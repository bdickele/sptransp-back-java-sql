package org.bdickele.sptransp.service;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.bdickele.sptransp.domain.Employee;
import org.bdickele.sptransp.domain.UserProfile;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static com.ninja_squad.dbsetup.Operations.sql;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class EmployeeServiceTest extends AbstractServiceTest {

    public static final Operation TEST_EMPLOYEE_DELETE = sequenceOf(
            sql("delete from ST_EMPLOYEE_AUD where full_name like 'EMPLOYEE%' "),
            sql("delete from ST_EMPLOYEE where full_name like 'EMPLOYEE%' "),
            sql("delete from ST_USER where uid_user like 'employ%' "));

    @Autowired
    private EmployeeService service;

    @Autowired
    private DataSource dataSource;


    @After
    public void after() {
        new DbSetup(new DataSourceDestination(dataSource), TEST_EMPLOYEE_DELETE).launch();
    }

    @Test
    public void insertion_and_update_of_employee_should_work() {
        new DbSetup(new DataSourceDestination(dataSource), TEST_EMPLOYEE_DELETE).launch();

        // ==== INSERTION ====

        Employee employee = service.create("EMPLOYEE_NAME", "ADMIN_ALL", "LAW_COMPLIANCE", 60, "testuser");

        String uid = employee.getUid();
        assertThat(uid).isEqualTo("employ1");
        assertThat(employee).isNotNull();
        assertThat(employee.getDepartment().getCode()).isEqualTo("LAW_COMPLIANCE");
        assertThat(employee.getSeniority().getValue()).isEqualTo(60);

        // ==== UPDATE ====

        Employee updatedEmployee = service.update(uid, "EMPLOYEE_NAME 2", "ADMIN_READER",
                "SHUTTLE_COMPLIANCE", 80, "test2");

        assertThat(updatedEmployee.getFullName()).isEqualTo("EMPLOYEE_NAME 2");
        assertThat(updatedEmployee.getProfile()).isEqualTo(UserProfile.ADMIN_READER);
        assertThat(updatedEmployee.getDepartment().getCode()).isEqualTo("SHUTTLE_COMPLIANCE");
        assertThat(updatedEmployee.getSeniority().getValue()).isEqualTo(80);
        assertThat(updatedEmployee.getUpdateUser()).isEqualTo("test2");
    }
}
