package org.bdickele.sptransp.domain.audit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
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
@EqualsAndHashCode(of = {"pk"}, doNotUseGetters = true)
@ToString(of = {"pk", "department", "seniority"}, doNotUseGetters = true)
@Getter
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

    public Integer getRank() {
        return pk.getRank();
    }

    public boolean canBeAppliedBy(Department department, Seniority seniority) {
        return this.department.getId().equals(department.getId()) && seniority.ge(this.seniority);
    }
}
