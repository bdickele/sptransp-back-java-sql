package org.bdickele.sptransp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.converter.SeniorityConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_EMPLOYEE")
@DiscriminatorValue(User.USER_TYPE_EMPLOYEE)
public class Employee extends User implements Serializable {

    private static final long serialVersionUID = -7870220121179537659L;

    @Column(name = "FULL_NAME")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTMENT")
    private Department department;

    @Column(name = "SENIORITY")
    @Convert(converter = SeniorityConverter.class)
    private Seniority seniority;


    /**
     * Build method for an employee
     * @param id
     * @param uid
     * @param fullName
     * @param department
     * @param seniority
     * @param creationUserUid
     * @return
     */
    public static Employee build(Long id, String uid, String fullName, Department department,
                                 Seniority seniority, String creationUserUid) {
        Employee e = new Employee();
        e.id = id;
        e.version = 0;
        e.uid = uid;
        e.profile = Profile.READER_ALL;
        e.fullName = fullName;
        e.department = department;
        e.seniority = seniority;

        LocalDateTime date = LocalDateTime.now();
        e.creationDate = date;
        e.updateDate = date;

        e.creationUser = creationUserUid;
        e.updateUser = creationUserUid;

        return e;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        Employee other = (Employee) obj;
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
                .append("fullName", fullName)
                .append("department", department.getName())
                .append("seniority", seniority.getValue())
                .toString();
    }
}
