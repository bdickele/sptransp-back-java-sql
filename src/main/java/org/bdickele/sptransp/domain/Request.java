package org.bdickele.sptransp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.audit.AgreementRuleAud;
import org.bdickele.sptransp.domain.audit.AgreementRuleVisaAud;
import org.bdickele.sptransp.domain.converter.RequestAgreementStatusConverter;
import org.bdickele.sptransp.domain.converter.RequestOverallStatusConverter;

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
@SequenceGenerator(name="SEQ_MAIN", sequenceName="SEQ_MAIN")
public class Request implements Serializable {

    private static final long serialVersionUID = 5545538909897599789L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MAIN")
    private Long id;

    @Version
    private Integer version;

    @Column(name = "UID")
    private String uid;

    @ManyToOne
    @JoinColumn(name = "ID_CUSTOMER")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ID_GOOD")
    private Good good;

    @ManyToOne
    @JoinColumn(name = "ID_DESTINATION")
    private Destination destination;

    @Column(name = "OVERALL_STATUS")
    @Convert(converter = RequestOverallStatusConverter.class)
    private RequestOverallStatus overallStatus;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "ID_RULE", referencedColumnName = "ID_RULE"),
            @JoinColumn(name = "RULE_VERSION", referencedColumnName = "VERSION")
    })
    private AgreementRuleAud ruleAud;

    @Column(name = "AGREEMENT_STATUS")
    @Convert(converter = RequestAgreementStatusConverter.class)
    private RequestAgreementStatus agreementStatus;

    @Column(name = "CANCELLATION_COMMENT")
    private String cancellationComment;

    // -1 when request is refused or granted
    @Column(name = "NEXT_AGREEMENT_VISA_RANK")
    private int nextAgreementVisaRank;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_REQUEST")
    private List<RequestAgreementVisa> agreementVisas;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "CREATION_USER")
    private String creationUser;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "UPDATE_USER")
    private String updateUser;


    /**
     * Build method for a request
     * @param uid
     * @param customer
     * @param good
     * @param destination
     * @param ruleVersion
     * @return
     */
    public static Request build(Long id, String uid, Customer customer, Good good,
                                Destination destination, AgreementRuleAud ruleVersion) {
        Request r = new Request();
        r.id = id;
        r.version = 0;
        r.uid = uid;
        r.customer = customer;
        r.good = good;
        r.destination = destination;
        r.ruleAud = ruleVersion;
        r.overallStatus = RequestOverallStatus.PENDING;
        r.agreementStatus = RequestAgreementStatus.PENDING;
        r.nextAgreementVisaRank = 0;
        r.agreementVisas = new ArrayList<>();

        LocalDateTime date = LocalDateTime.now();
        r.creationDate = date;
        r.updateDate = date;

        r.creationUser = customer.getUid();
        r.updateUser = customer.getUid();

        return r;
    }

    /**
     * Method to call when we want to apply (grant or deny) an agreement visa
     * @param employee
     * @param status
     * @param comment
     * @param department
     * @param seniority
     * @param creationDate We can pass creation date so that it can be synchronized with update date of request
     * @return
     */
    public Request applyAgreementVisa(Employee employee, RequestAgreementVisaStatus status, String comment,
                                    Department department, Seniority seniority, LocalDateTime creationDate) {
        if (!waitsForAnAgreementVisa()) {
            throw REQUEST_DOES_NOT_EXPECT_ANY_AGREEMENT_VISA.exception();
        }

        if (userHasAlreadyAppliedAVisa(employee.getId())) {
            throw EMPLOYEE_HAS_ALREADY_APPLIED_A_VISA.exception(employee.getFullName());
        }

        AgreementRuleVisaAud nextExpectedVisa = getNextExpectedAgreementVisa()
                .orElseThrow(() -> COULD_NOT_FIND_NEXT_EXPECTED_AGREEMENT_VISA.exception());

        if (!nextExpectedVisa.canBeAppliedBy(department, seniority)) {
            throw VISA_TO_APPLY_DOESNT_MATCH_NEXT_EXPECTED_ONE.exception(
                    department.getName(), seniority.getValue(),
                    nextExpectedVisa.getDepartment(), nextExpectedVisa.getSeniority().getValue());
        }

        RequestAgreementVisa appliedVisa = RequestAgreementVisa.build(null, employee.getId(), status,
                nextAgreementVisaRank, comment, department, seniority, creationDate);

        addAgreementVisa(appliedVisa);

        return this;
    }

    /**
     * We know what is the next visa to apply. Let's add it to the list of applied visas and check what is the new
     * status of the request
     * @param appliedVisa Visa to add
     */
    private void addAgreementVisa(RequestAgreementVisa appliedVisa) {
        agreementVisas.add(appliedVisa);

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
            throw UNEXPECTED_ERROR.exception("Unknown status of agreement visa: " + lastAppliedVisaStatus);
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
                .filter(v -> v.getEmployeeId().equals(employeeId))
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

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public String getUid() {
        return uid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Good getGood() {
        return good;
    }

    public Destination getDestination() {
        return destination;
    }

    public RequestOverallStatus getOverallStatus() {
        return overallStatus;
    }

    public AgreementRuleAud getRuleAud() {
        return ruleAud;
    }

    public RequestAgreementStatus getAgreementStatus() {
        return agreementStatus;
    }

    public String getCancellationComment() {
        return cancellationComment;
    }

    public void setCancellationComment(String cancellationComment) {
        this.cancellationComment = cancellationComment;
    }

    public int getNextAgreementVisaRank() {
        return nextAgreementVisaRank;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Request other = (Request) obj;
        return new EqualsBuilder()
                .append(this.id, other.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 11)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("uid", uid)
                .append("customer", customer.getFullName())
                .append("destination", destination.getName())
                .append("good", good.getName())
                .append("overallStatus", overallStatus)
                .append("agreementStatus", agreementStatus)
                .toString();
    }
}
