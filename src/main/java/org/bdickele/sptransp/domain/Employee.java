package org.bdickele.sptransp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
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
@EqualsAndHashCode(callSuper=true, of={}, doNotUseGetters = true)
@Getter
public class Employee extends User implements Serializable {

    private static final long serialVersionUID = -7870220121179537659L;

    public static final Seniority SENIORITY_MIN = Seniority.of(0);
    public static final Seniority SENIORITY_MAX = Seniority.of(100);

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
        e.version = 1;
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
        checkUid();
        checkFullName();
        checkProfile();
        checkDepartment();
        checkSeniority();
        checkCreationInfo();
        checkUpdateInfo();
    }

    public void checkUid() {
        if (StringUtils.isEmpty(uid)) throw SpTranspBizError.EMPLOYEE_MISSING_VALUE.exception("uid");
    }

    public void checkFullName() {
        if (StringUtils.isEmpty(fullName)) throw SpTranspBizError.EMPLOYEE_MISSING_VALUE.exception("name");
    }

    public void checkProfile() {
        if (profile==null) throw SpTranspBizError.EMPLOYEE_MISSING_VALUE.exception("profile");
    }

    public void checkDepartment() {
        if (department==null) throw SpTranspBizError.EMPLOYEE_MISSING_VALUE.exception("department");
    }

    public void checkSeniority() {
        if (seniority==null) throw SpTranspBizError.EMPLOYEE_MISSING_VALUE.exception("seniority");

        if (seniority.lt(SENIORITY_MIN) || seniority.gt(SENIORITY_MAX))
            throw SpTranspBizError.EMPLOYEE_INCORRECT_SENIORITY.exception(
                    SENIORITY_MIN.getValue(), SENIORITY_MAX.getValue());
    }

    public void checkCreationInfo() {
        if (StringUtils.isEmpty(creationUser)) throw SpTranspTechError.MISSING_INFORMATION.exception("creation user");
        if (creationDate==null) throw SpTranspTechError.MISSING_INFORMATION.exception("creation date");
    }

    public void checkUpdateInfo() {
        if (StringUtils.isEmpty(updateUser)) throw SpTranspTechError.MISSING_INFORMATION.exception("update user");
        if (updateDate==null) throw SpTranspTechError.MISSING_INFORMATION.exception("update date");
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("uid", uid)
                .append("fullName", fullName)
                .append("department", department)
                .append("seniority", seniority)
                .toString();
    }
}
