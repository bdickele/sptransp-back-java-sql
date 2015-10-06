package org.bdickele.sptransp.service;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.bdickele.sptransp.domain.Employee;
import org.bdickele.sptransp.repository.EmployeeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.Date;

import static com.ninja_squad.dbsetup.Operations.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class EmployeeServiceTest extends AbstractServiceTest {

    public static final Date NOW = new Date();

    public static final Operation TEST_EMPLOYEE_DELETE = sequenceOf(
            sql("delete from ST_EMPLOYEE where full_name like 'EMPLOYEE1_NAME%' "),
            sql("delete from ST_USER where uid_user = 'EMPLOYEE1' "));

    public static final Operation TEST_EMPLOYEE_INSERT = sequenceOf(
            insertInto("ST_USER")
                .columns("id", "version", "user_type", "user_password", "uid_user", "user_profile",
                        "creation_date", "creation_user", "update_date", "update_user")
                .values(1000, 1, "E", "pwd", "EMPLOYEE1", "ADMIN_ALL", NOW, "test", NOW, "test")
                .build(),
            insertInto("ST_EMPLOYEE")
                    .columns("id", "full_name", "id_department", "seniority")
                    .values(1000, "EMPLOYEE1_NAME", 1, 60)
                    .build());

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private DataSource dataSource;


    @Test
    public void update_of_employee_should_work() {
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), TEST_EMPLOYEE_DELETE);
        dbSetup.launch();

        String uid = "EMPLOYEE1";
        Employee employee = repository.findByUid(uid);
        assertThat(employee).isNull();

        dbSetup = new DbSetup(new DataSourceDestination(dataSource), TEST_EMPLOYEE_INSERT);
        dbSetup.launch();

        employee = repository.findByUid(uid);
        assertThat(employee).isNotNull();

        assertThat(employee.getDepartment().getCode()).isEqualTo("LAW_COMPLIANCE");
        assertThat(employee.getSeniority().getValue()).isEqualTo(60);

        int currentVersion = employee.getVersion();

        Employee updatedEmployee = service.update(uid, "EMPLOYEE1_NAME 2", "SHUTTLE_COMPLIANCE", 80, "test2");

        assertThat(updatedEmployee.getFullName()).isEqualTo("EMPLOYEE1_NAME 2");
        assertThat(updatedEmployee.getDepartment().getCode()).isEqualTo("SHUTTLE_COMPLIANCE");
        assertThat(updatedEmployee.getSeniority().getValue()).isEqualTo(80);
        assertThat(updatedEmployee.getVersion()).isEqualTo(currentVersion + 1);
        assertThat(updatedEmployee.getUpdateUser()).isEqualTo("test2");
    }
}
