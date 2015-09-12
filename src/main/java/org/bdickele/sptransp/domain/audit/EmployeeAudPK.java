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
public class EmployeeAudPK implements Serializable {

    private static final long serialVersionUID = 584182696871123483L;

    private Long id;

    private Integer version;


    /** Constructor */
    public EmployeeAudPK() {
        //
    }

    public static EmployeeAudPK build(Long id, Integer version) {
        EmployeeAudPK pk = new EmployeeAudPK();
        pk.id = id;
        pk.version = version;
        return pk;
    }

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
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
        EmployeeAudPK other = (EmployeeAudPK) obj;
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
