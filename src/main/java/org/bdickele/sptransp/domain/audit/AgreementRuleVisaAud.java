package org.bdickele.sptransp.domain.audit;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.AgreementRule;
import org.bdickele.sptransp.domain.AgreementRuleVisa;
import org.bdickele.sptransp.domain.Department;
import org.bdickele.sptransp.domain.Seniority;
import org.bdickele.sptransp.domain.converter.SeniorityConverter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_AGR_RULE_VISA_AUD")
public class AgreementRuleVisaAud implements Serializable {

    private static final long serialVersionUID = 5923236734333092025L;

    @EmbeddedId
    private AgreementRuleVisaAudPK pk;

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTMENT")
    private Department department;

    @Column(name = "SENIORITY")
    @Convert(converter = SeniorityConverter.class)
    private Seniority seniority;


    /**
     * Builds a new version of an Agreement Rule for audit, based on an existing rule (increments version by 1)
     * @return New instance of AgreementRuleVisaAud
     */
    public static AgreementRuleVisaAud build(AgreementRule rule, AgreementRuleVisa visa) {
        AgreementRuleVisaAud audit = new AgreementRuleVisaAud();
        audit.pk = AgreementRuleVisaAudPK.build(rule.getId(), rule.getVersion()+1, visa.getRank());
        audit.department = visa.getDepartment();
        audit.seniority = visa.getSeniority();
        return audit;
    }

    public boolean canBeAppliedBy(Department department, Seniority seniority) {
        return this.department.getId().equals(department.getId()) && seniority.ge(this.seniority);
    }

    public AgreementRuleVisaAudPK getPk() {
        return pk;
    }

    public Integer getRank() {
        return pk.getRank();
    }

    public Department getDepartment() {
        return department;
    }

    public Seniority getSeniority() {
        return seniority;
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
        AgreementRuleVisaAud other = (AgreementRuleVisaAud) obj;
        return new EqualsBuilder()
                .append(this.pk, other.pk)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 11)
                .append(pk)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("idRule", pk.getIdRule())
                .append("version", pk.getVersion())
                .append("rank", pk.getRank())
                .append("department", department.getName())
                .append("seniority", seniority.getValue())
                .toString();
    }
}
