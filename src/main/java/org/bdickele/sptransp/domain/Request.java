package org.bdickele.sptransp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.audit.AgreementRuleAud;
import org.bdickele.sptransp.domain.converter.RequestAgreementStatusConverter;
import org.bdickele.sptransp.domain.converter.RequestOverallStatusConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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
    @Column(name = "NEXT_VSA_RANK")
    private int nextVisaRank;

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

        LocalDateTime date = LocalDateTime.now();
        r.creationDate = date;
        r.updateDate = date;

        r.creationUser = customer.getUid();
        r.updateUser = customer.getUid();

        return r;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public RequestOverallStatus getOverallStatus() {
        return overallStatus;
    }

    public void setOverallStatus(RequestOverallStatus overallStatus) {
        this.overallStatus = overallStatus;
    }

    public AgreementRuleAud getRuleAud() {
        return ruleAud;
    }

    public void setRuleAud(AgreementRuleAud ruleAud) {
        this.ruleAud = ruleAud;
    }

    public RequestAgreementStatus getAgreementStatus() {
        return agreementStatus;
    }

    public void setAgreementStatus(RequestAgreementStatus agreementStatus) {
        this.agreementStatus = agreementStatus;
    }

    public String getCancellationComment() {
        return cancellationComment;
    }

    public void setCancellationComment(String cancellationComment) {
        this.cancellationComment = cancellationComment;
    }

    public int getNextVisaRank() {
        return nextVisaRank;
    }

    public void setNextVisaRank(int nextVisaRank) {
        this.nextVisaRank = nextVisaRank;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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
