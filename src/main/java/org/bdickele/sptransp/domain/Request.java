package org.bdickele.sptransp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.bdickele.sptransp.domain.audit.AgreementRuleAud;
import org.bdickele.sptransp.domain.audit.AgreementRuleVisaAud;
import org.bdickele.sptransp.domain.converter.LocalDateTimeConverter;
import org.bdickele.sptransp.domain.converter.RequestAgreementStatusConverter;
import org.bdickele.sptransp.domain.converter.RequestOverallStatusConverter;
import org.bdickele.sptransp.exception.SpTranspTechError;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.bdickele.sptransp.exception.SpTranspBizError.*;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_REQUEST")
@SequenceGenerator(name="SEQ_MAIN", sequenceName=DomainConst.SEQUENCE_NAME)
@EqualsAndHashCode(of = "id", doNotUseGetters = true)
@ToString(of = {"id", "reference", "customer", "departure", "arrival", "goods", "overallStatus", "agreementStatus"}, doNotUseGetters = true)
@Getter
public class Request implements Serializable {

    private static final long serialVersionUID = 5545538909897599789L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MAIN")
    private Long id;

    @Version
    private Integer version;

    @Column(name = "REFERENCE")
    private String reference;

    @ManyToOne
    @JoinColumn(name = "ID_CUSTOMER")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ID_GOODS")
    private Goods goods;

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTURE")
    private Destination departure;

    @ManyToOne
    @JoinColumn(name = "ID_ARRIVAL")
    private Destination arrival;

    @Column(name = "OVERALL_STATUS")
    @Convert(converter = RequestOverallStatusConverter.class)
    private RequestOverallStatus overallStatus;

    @OneToOne
    @JoinColumns(value = {
            @JoinColumn(name = "ID_RULE", referencedColumnName = "ID_RULE"),
            @JoinColumn(name = "RULE_VERSION", referencedColumnName = "VERSION")})
    private AgreementRuleAud ruleAud;

    @Column(name = "AGREEMENT_STATUS")
    @Convert(converter = RequestAgreementStatusConverter.class)
    private RequestAgreementStatus agreementStatus;

    @Column(name = "CANCELLATION_COMMENT")
    private String cancellationComment;

    // -1 when request is refused or granted
    @Column(name = "NEXT_AGREEMENT_VISA_RANK")
    private int nextAgreementVisaRank;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "request")
    @OrderBy("CREATION_DATE ASC ")
    private List<RequestAgreementVisa> agreementVisas = new ArrayList<>();

    @Column(name = "CREATION_DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime creationDate;

    @Column(name = "CREATION_USER")
    private String creationUser;

    @Column(name = "UPDATE_DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime updateDate;

    @Column(name = "UPDATE_USER")
    @Getter
    private String updateUser;


    /**
     * Build method for a request
     * @param reference
     * @param customer
     * @param goods
     * @param departure
     * @param arrival
     * @param ruleVersion
     * @return
     */
    public static Request build(Long id, String reference, Customer customer, Goods goods,
                                Destination departure, Destination arrival, AgreementRuleAud ruleVersion) {
        Request r = new Request();
        r.id = id;
        r.version = 1;
        r.reference = reference;
        r.customer = customer;
        r.goods = goods;
        r.departure = departure;
        r.arrival = arrival;
        r.ruleAud = ruleVersion;
        r.overallStatus = RequestOverallStatus.WAITING_FOR_VALIDATION;
        r.agreementStatus = RequestAgreementStatus.PENDING;
        r.nextAgreementVisaRank = 0;
        r.agreementVisas = new ArrayList<>();

        LocalDateTime date = LocalDateTime.now();
        r.creationDate = date;
        r.updateDate = date;

        r.creationUser = customer.getUid();
        r.updateUser = customer.getUid();

        r.checkValues();

        return r;
    }

    public void checkValues() {
        checkReference();
        checkCustomer();
        checkGoods();
        checkDeparture();
        checkArrival();
        checkDepartureAndArrival();
        checkRuleAud();
        checkCreationInfo();
        checkUpdateInfo();
    }

    public void checkReference() {
        if (StringUtils.isEmpty(reference)) throw REQUEST_MISSING_VALUE.exception("reference");
    }

    public void checkCustomer() {
        if (customer==null) throw REQUEST_MISSING_VALUE.exception("customer");
    }

    public void checkGoods() {
        if (goods==null) throw REQUEST_MISSING_VALUE.exception("goods");
    }

    public void checkDeparture() {
        if (departure==null) throw REQUEST_MISSING_VALUE.exception("departure");
    }

    public void checkArrival() {
        if (arrival==null) throw REQUEST_MISSING_VALUE.exception("arrival");
    }

    public void checkDepartureAndArrival() {
        if (departure.getCode().equals(arrival.getCode())) throw DESTINATION_AND_ARRIVAL_ARE_THE_SAME.exception();
    }

    public void checkRuleAud() {
        if (ruleAud==null) throw REQUEST_MISSING_VALUE.exception("rule");
        if (!ruleAud.getAllowed()) throw REQUEST_NOT_ALLOWED.exception(goods.getName(), arrival.getName());
    }

    public void checkCreationInfo() {
        if (StringUtils.isEmpty(creationUser)) throw SpTranspTechError.MISSING_INFORMATION.exception("creation user");
        if (creationDate==null) throw SpTranspTechError.MISSING_INFORMATION.exception("creation date");
    }

    public void checkUpdateInfo() {
        if (StringUtils.isEmpty(updateUser)) throw SpTranspTechError.MISSING_INFORMATION.exception("update user");
        if (updateDate==null) throw SpTranspTechError.MISSING_INFORMATION.exception("update date");
    }

    /**
     * Method to call when we want to apply (grant or deny) an agreement visa
     * @param employee
     * @param visaStatus
     * @param comment
     * @return
     */
    public Request applyAgreementVisa(Employee employee, RequestAgreementVisaStatus visaStatus, String comment) {
        if (!waitsForAnAgreementVisa()) {
            throw REQUEST_DOES_NOT_EXPECT_ANY_AGREEMENT_VISA.exception();
        }

        if (userHasAlreadyAppliedAVisa(employee.getId())) {
            throw EMPLOYEE_HAS_ALREADY_APPLIED_A_VISA.exception(employee.getFullName());
        }

        AgreementRuleVisaAud nextExpectedVisa = getNextExpectedAgreementVisa()
                .orElseThrow(() -> COULD_NOT_FIND_NEXT_EXPECTED_AGREEMENT_VISA.exception());

        Department department = employee.getDepartment();
        Seniority seniority = employee.getSeniority();

        if (!nextExpectedVisa.canBeAppliedBy(department, seniority)) {
            throw VISA_TO_APPLY_DOESNT_MATCH_NEXT_EXPECTED_ONE.exception(
                    department.getName(), seniority.getValue(),
                    nextExpectedVisa.getDepartment().getName(), nextExpectedVisa.getSeniority().getValue());
        }

        LocalDateTime now = LocalDateTime.now();

        RequestAgreementVisa appliedVisa = RequestAgreementVisa.build(this, null, employee, visaStatus,
                nextAgreementVisaRank, comment, department, seniority, now);

        addAgreementVisa(appliedVisa);

        updateUser = employee.getUid();
        updateDate = now;

        return this;
    }

    /**
     * We know what is the next visa to apply. Let's add it to the list of applied visas and check what is the new
     * visaStatus of the request
     * @param appliedVisa Visa to add
     */
    private void addAgreementVisa(RequestAgreementVisa appliedVisa) {
        getAgreementVisas().add(appliedVisa);

        RequestAgreementVisaStatus lastAppliedVisaStatus = appliedVisa.getStatus();

        // If last visa applied has been denied, no need to carry on: request is refused
        if (lastAppliedVisaStatus == RequestAgreementVisaStatus.DENIED) {
            nextAgreementVisaRank = -1;
            agreementStatus = RequestAgreementStatus.REFUSED;
            overallStatus = RequestOverallStatus.REFUSED;
        } else if (lastAppliedVisaStatus == RequestAgreementVisaStatus.GRANTED) {
            // Let's check if granted visa is the last one
            Integer rankOfLastExpectedVisa = ruleAud.getVisas().stream()
                    .max(Comparator.comparing(AgreementRuleVisaAud::getRank))
                    .get().getRank();

            if (appliedVisa.getRank().equals(rankOfLastExpectedVisa)) {
                nextAgreementVisaRank = -1;
                agreementStatus = RequestAgreementStatus.GRANTED;
                overallStatus = RequestOverallStatus.VALIDATED;
            } else {
                nextAgreementVisaRank++;
            }
        } else {
            // Not supposed to happen except business rule gets changed
            throw UNEXPECTED_ERROR.exception("Unknown visaStatus of agreement visa: " + lastAppliedVisaStatus);
        }
    }

    /**
     * @return True if that request is still waiting for an agreement visa
     */
    public boolean waitsForAnAgreementVisa() {
        return agreementStatus==RequestAgreementStatus.PENDING;
    }

    /**
     * @param employeeId
     * @return True if passed employee has already applied an agreement visa for that request
     */
    public boolean userHasAlreadyAppliedAVisa(Long employeeId) {
        return agreementVisas.stream()
                .filter(v -> v.getEmployee().getId().equals(employeeId))
                .findFirst()
                .isPresent();
    }

    /**
     * @return Next expected agreement visa (if any)
     */
    public Optional<AgreementRuleVisaAud> getNextExpectedAgreementVisa() {
        return ruleAud.getVisas().stream()
                .filter(v -> v.getRank().equals(nextAgreementVisaRank))
                .findFirst();
    }
}
