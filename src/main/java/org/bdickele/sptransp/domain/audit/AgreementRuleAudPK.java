package org.bdickele.sptransp.domain.audit;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by Bertrand DICKELE
 */
public class AgreementRuleAudPK implements Serializable {

    private static final long serialVersionUID = 7541457513897132559L;

    private Long id;

    private Long version;


    /** Constructor */
    public AgreementRuleAudPK() {
        //
    }

    public static AgreementRuleAudPK build(Long id, Long version) {
        AgreementRuleAudPK pk = new AgreementRuleAudPK();
        pk.id = id;
        pk.version = version;
        return pk;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
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
        AgreementRuleAudPK other = (AgreementRuleAudPK) obj;
        return new EqualsBuilder()
                .append(this.id, other.id)
                .append(this.version, other.version)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 11)
                .append(id)
                .append(version)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("version", version)
                .toString();
    }
}
