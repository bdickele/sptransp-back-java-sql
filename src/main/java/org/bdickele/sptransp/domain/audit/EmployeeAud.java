package org.bdickele.sptransp.domain.audit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bdickele.sptransp.domain.Employee;
import org.bdickele.sptransp.domain.converter.LocalDateTimeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_EMPLOYEE_AUD")
@EqualsAndHashCode(of = {"pk"}, doNotUseGetters = true)
@ToString(of = {"pk", "uid", "fullName", "department", "seniority"}, doNotUseGetters = true)
@Getter
public class EmployeeAud implements Serializable {

    private static final long serialVersionUID = 4822318330160163008L;

    @EmbeddedId
    private EmployeeAudPK pk;

    @Column(name = "UID_EMPLOYEE")
    private String uid;

    @Column(name = "PROFILE")
    private String profile;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "ID_DEPARTMENT")
    private Long department;

    @Column(name = "SENIORITY")
    private Integer seniority;

    @Column(name = "VERSION_DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime versionDate;

    @Column(name = "VERSION_USER")
    private String versionUser;


    /**
     * Builds a new version of Employee for audit, based on an existing Employee (increments version by 1)
     * @param employee Base data
     * @param version
     * @return New instance of EmployeeAud
     */
    public static EmployeeAud build(Employee employee, Integer version) {
        EmployeeAud audit = new EmployeeAud();
        audit.pk = EmployeeAudPK.build(employee.getId(), version);
        audit.uid = employee.getUid();
        audit.fullName = employee.getFullName();
        audit.profile = employee.getProfile().getCode();
        audit.department = employee.getDepartment().getId();
        audit.seniority = employee.getSeniority().getValue();
        audit.versionDate = employee.getUpdateDate();
        audit.versionUser = employee.getUpdateUser();
        return audit;
    }
}
