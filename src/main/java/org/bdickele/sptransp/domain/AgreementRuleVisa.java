package org.bdickele.sptransp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
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

    public Long getId() {
        return id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public void setSeniority(Seniority seniority) {
        this.seniority = seniority;
    }

    public void setSeniority(int seniorityValue) {
        this.seniority = new Seniority(seniorityValue);
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
        AgreementRuleVisa other = (AgreementRuleVisa) obj;
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
                .append("destination", department.getName())
                .append("good", seniority.getValue())
                .toString();
    }
}
