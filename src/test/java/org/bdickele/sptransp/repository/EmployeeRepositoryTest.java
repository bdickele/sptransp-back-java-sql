package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;

/**
 * Created by Bertrand DICKELE
 */
public class EmployeeRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private DataSource dataSource;


    @Test
    public void findAll_should_work() {
        List<Employee> employees = repository.findAllByOrderByFullNameAsc();
        assertThat(employees.size()).isGreaterThanOrEqualTo(20);
        assertThat(employees)
                .extracting("uid", "fullName", "department.code", "seniority.value").contains(
                tuple("kvcquz31", "Kathleen Carpenter", "JOURNEY_SUPERVISION", 20),
                tuple("whlofu42", "Helen Cox", "LAW_COMPLIANCE", 20));
    }
}
