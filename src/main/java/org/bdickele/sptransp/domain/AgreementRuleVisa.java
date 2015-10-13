package org.bdickele.sptransp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
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
@SequenceGenerator(name="SEQ_MAIN", sequenceName="SEQ_MAIN")
@EqualsAndHashCode(of = "id", doNotUseGetters = true)
@ToString(of = {"id", "rank", "department", "seniority"}, doNotUseGetters = true)
@Getter
public class AgreementRuleVisa implements Serializable {

    private static final long serialVersionUID = 7574694654323540710L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQ_MAIN")
    private Long id;

    @Column(name = "RANK")
    private Integer rank;

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTMENT")
    private Department department;

    @Column(name = "SENIORITY")
    @Convert(converter = SeniorityConverter.class)
    private Seniority seniority;


    /**
     * Build method for an agreement visa
     * @param id
     * @param rank
     * @param department
     * @param seniority
     * @return
     */
    public static AgreementRuleVisa build(Long id, Integer rank, Department department, Seniority seniority) {
        AgreementRuleVisa v = new AgreementRuleVisa();
        v.id = id;
        v.rank = rank;
        v.department = department;
        v.seniority = seniority;
        return v;
    }
}
