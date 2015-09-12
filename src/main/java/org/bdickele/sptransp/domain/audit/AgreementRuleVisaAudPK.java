package org.bdickele.sptransp.domain.audit;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Bertrand DICKELE
 */
@Embeddable
public class AgreementRuleVisaAudPK implements Serializable {

    private static final long serialVersionUID = -8995397281778177804L;

    private Long idRule;

    private Long version;

    private Integer rank;


    /** Constructor */
    public AgreementRuleVisaAudPK() {
        //
    }

    public static AgreementRuleVisaAudPK build(Long idRule, Long version, Integer rank) {
        AgreementRuleVisaAudPK pk = new AgreementRuleVisaAudPK();
        pk.idRule = idRule;
        pk.version = version;
        pk.rank = rank;
        return pk;
    }

    public Long getIdRule() {
        return idRule;
    }

    public Long getVersion() {
        return version;
    }

    public Integer getRank() {
        return rank;
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
        AgreementRuleVisaAudPK other = (AgreementRuleVisaAudPK) obj;
        return new EqualsBuilder()
                .append(this.idRule, other.idRule)
                .append(this.version, other.version)
                .append(this.rank, other.rank)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 11)
                .append(idRule)
                .append(version)
                .append(rank)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("idRule", idRule)
                .append("version", version)
                .append("rank", rank)
                .toString();
    }
}
