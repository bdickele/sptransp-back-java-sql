package org.bdickele.sptransp.domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.converter.SeniorityConverter;
import org.bdickele.sptransp.exception.SpTranspBizError;
import org.bdickele.sptransp.exception.SpTranspTechError;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public static final Seniority SENIORITY_MIN = new Seniority(0);
    public static final Seniority SENIORITY_MAX = new Seniority(100);

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
     * @param creationUser
     * @param passwordEncoder
     * @return
     */
    public static Employee build(Long id, String uid, String fullName, UserProfile profile,
                                 Department department, Seniority seniority, String creationUser,
                                 PasswordEncoder passwordEncoder) {
        Employee e = new Employee();
        e.id = id;
        e.version = 0;
        e.password = passwordEncoder.encode("changeme");
        e.uid = uid;
        e.profile = profile;
        e.fullName = fullName;
        e.department = department;
        e.seniority = seniority;

        LocalDateTime date = LocalDateTime.now();
        e.creationDate = date;
        e.updateDate = date;

        e.creationUser = creationUser;
        e.updateUser = creationUser;

        e.checkValues();

        return e;
    }

    public void update(String fullName, UserProfile profile, Department department,
                       Seniority seniority, String updateUser) {
        this.fullName = fullName;
        this.profile = profile;
        this.department = department;
        this.seniority = seniority;
        this.updateUser = updateUser;
        this.updateDate = LocalDateTime.now();

        checkValues();
    }

    public void checkValues() {
        checkUid(uid);
        checkFullName(fullName);
        checkProfile(profile);
        checkDepartment(department);
        checkSeniority(seniority);
        checkOperationUser(creationUser);
    }

    public static void checkUid(String uid) {
        if (StringUtils.isEmpty(uid)) throw SpTranspBizError.EMPLOYEE_MISSING_VALUE.exception("uid");
    }

    public static void checkFullName(String fullName) {
        if (StringUtils.isEmpty(fullName)) throw SpTranspBizError.EMPLOYEE_MISSING_VALUE.exception("name");
    }

    public static void checkProfile(UserProfile profile) {
        if (profile==null) throw SpTranspBizError.EMPLOYEE_MISSING_VALUE.exception("profile");
    }

    public static void checkDepartment(Department department) {
        if (department==null) throw SpTranspBizError.EMPLOYEE_MISSING_VALUE.exception("department");
    }

    public static void checkSeniority(Seniority seniority) {
        if (seniority==null) throw SpTranspBizError.EMPLOYEE_MISSING_VALUE.exception("seniority");

        if (seniority.lt(SENIORITY_MIN) || seniority.gt(SENIORITY_MAX))
            throw SpTranspBizError.EMPLOYEE_INCORRECT_SENIORITY.exception(
                    SENIORITY_MIN.getValue(), SENIORITY_MAX.getValue());
    }

    public static void checkOperationUser(String user) {
        if (StringUtils.isEmpty(user)) throw SpTranspTechError.OPERATION_USER_MISSING.exception();
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
