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
        List<Department> departments = repository.findAllByOrderByNameAsc();
        assertThat(departments).hasSize(5);

        assertThat(departments).extracting("id", "code", "name").containsExactly(
                tuple(3L, "GOODS_INSPECTION", "Goods inspection"),
                tuple(5L, "HR", "Human resources"),
                tuple(4L, "JOURNEY_SUPERVISION", "Journey supervision"),
                tuple(1L, "LAW_COMPLIANCE", "Law compliance"),
                tuple(2L, "SHUTTLE_COMPLIANCE", "Shuttle compliance"));
    }
}
