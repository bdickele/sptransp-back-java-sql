package org.bdickele.sptransp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.converter.LocalDateTImeConverter;
import org.bdickele.sptransp.domain.converter.SeniorityConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_EMPLOYEE")
@SequenceGenerator(name="SEQ_MAIN", sequenceName="SEQ_MAIN")
public class Employee implements Serializable {

    private static final long serialVersionUID = -7870220121179537659L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MAIN")
    private Long id;

    @Version
    private Integer version;

    @Column(name = "UID")
    private String uid;

    @Column(name = "FULL_NAME")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTMENT")
    private Department department;

    @Column(name = "SENIORITY")
    @Convert(converter = SeniorityConverter.class)
    private Seniority seniority;

    @Column(name = "CREATION_DATE")
    @Convert(converter = LocalDateTImeConverter.class)
    private LocalDateTime creationDate;

    @Column(name = "CREATION_USER")
    private String creationUser;

    @Column(name = "UPDATE_DATE")
    @Convert(converter = LocalDateTImeConverter.class)
    private LocalDateTime updateDate;

    @Column(name = "UPDATE_USER")
    private String updateUser;


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
        e.uid = uid;
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

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
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
