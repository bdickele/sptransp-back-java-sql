package org.bdickele.sptransp.service;

import org.bdickele.sptransp.domain.Employee;
import org.bdickele.sptransp.repository.EmployeeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class EmployeeServiceTest extends AbstractServiceTest {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeRepository repository;


    @Test
    public void update_of_employee_should_work() {
        String uid = "doejoh01";
        Employee employee = repository.findByUid(uid);

        assertThat(employee.getDepartment().getCode()).isEqualTo("LAW_COMPLIANCE");
        assertThat(employee.getSeniority().getValue()).isEqualTo(60);

        int currentVersion = employee.getVersion();

        Employee updatedEmployee = service.update(uid, "SHUTTLE_COMPLIANCE", 80, "testuser");

        assertThat(updatedEmployee.getDepartment().getCode()).isEqualTo("SHUTTLE_COMPLIANCE");
        assertThat(updatedEmployee.getSeniority().getValue()).isEqualTo(80);
        assertThat(updatedEmployee.getVersion()).isEqualTo(currentVersion + 1);
    }
}
