package org.bdickele.sptransp.domain.audit;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.Employee;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_EMPLOYEE_AUD")
public class EmployeeAud implements Serializable {

    private static final long serialVersionUID = 4822318330160163008L;

    @EmbeddedId
    private EmployeeAudPK pk;

    @Column(name = "UID")
    private String uid;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "ID_DEPARTMENT")
    private Long department;

    @Column(name = "SENIORITY")
    private Integer seniority;

    @Column(name = "VERSION_DATE")
    private LocalDateTime versionDate;

    @Column(name = "VERSION_USER")
    private String versionUser;


    /**
     * Builds a new version of Employee for audit, based on an existing Employee (increments version by 1)
     * @param employee Base data
     * @return New instance of EmployeeAud
     */
    public static EmployeeAud build(Employee employee) {
        EmployeeAud audit = new EmployeeAud();
        audit.pk = EmployeeAudPK.build(employee.getId(), employee.getVersion() + 1);
        audit.uid = employee.getUid();
        audit.fullName = employee.getFullName();
        audit.department = employee.getDepartment().getId();
        audit.seniority = employee.getSeniority().getValue();
        audit.versionDate = employee.getUpdateDate();
        audit.versionUser = employee.getUpdateUser();
        return audit;
    }

    public EmployeeAudPK getPk() {
        return pk;
    }

    public String getUid() {
        return uid;
    }

    public String getFullName() {
        return fullName;
    }

    public Long getDepartment() {
        return department;
    }

    public Integer getSeniority() {
        return seniority;
    }

    public LocalDateTime getVersionDate() {
        return versionDate;
    }

    public String getVersionUser() {
        return versionUser;
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
        EmployeeAud other = (EmployeeAud) obj;
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
                .append("id/version", pk.getId() + "/" + pk.getVersion())
                .append("uid", uid)
                .append("fullName", fullName)
                .append("department", department)
                .append("seniority", seniority)
                .toString();
    }
}
