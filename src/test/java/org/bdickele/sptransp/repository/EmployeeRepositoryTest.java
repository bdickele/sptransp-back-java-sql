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
        assertThat(employees)
                .hasSize(2)
                .extracting("uid", "fullName", "department.code", "seniority.value").containsExactly(
                    tuple("doejoh01", "John DOE", "LAW_COMPLIANCE", 60),
                    tuple("doejoh02", "John DOE 2", "LAW_COMPLIANCE", 60));
    }
}
