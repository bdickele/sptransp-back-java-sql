package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Department;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;

/**
 * Created by Bertrand DICKELE
 */
public class DepartmentRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private DepartmentRepository repository;


    @Test
    public void should_find_all_departments() {
        List<Department> departments = repository.findAll();
        assertThat(departments).hasSize(4);

        assertThat(departments).extracting("id", "name").containsExactly(
                tuple(1L, "Law compliance"),
                tuple(2L, "Shuttle compliance"),
                tuple(3L, "Good inspection"),
                tuple(4L, "Journey supervision"));
    }
}
