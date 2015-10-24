package org.bdickele.sptransp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bdickele.sptransp.domain.converter.SeniorityConverter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Visa required defined for an agreement rule
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_AGR_RULE_VISA")
@SequenceGenerator(name="SEQ_MAIN", sequenceName=DomainConst.SEQUENCE_NAME)
@EqualsAndHashCode(of = "id", doNotUseGetters = true)
@ToString(of = {"id", "rank", "department", "seniority"}, doNotUseGetters = true)
@Getter
public class AgreementRuleVisa implements Serializable {

    private static final long serialVersionUID = 7574694654323540710L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQ_MAIN")
    private Long id;

    @Column(name = "VISA_RANK")
    @Setter private Integer rank;

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTMENT")
    private Department department;

    @Column(name = "SENIORITY")
    @Convert(converter = SeniorityConverter.class)
    private Seniority seniority;

    @ManyToOne
    @JoinColumn(name = "ID_RULE")
    private AgreementRule rule;


    /**
     * Build method for an agreement visa
     * @param id
     * @param rank
     * @param department
     * @param seniority
     * @return
     */
    public static AgreementRuleVisa build(AgreementRule rule, Long id, Integer rank,
                                          Department department, Seniority seniority) {
        AgreementRuleVisa v = new AgreementRuleVisa();
        v.id = id;
        v.rank = rank;
        v.department = department;
        v.seniority = seniority;
        v.rule = rule;
        return v;
    }
}
