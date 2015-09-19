package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Department;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by bdickele
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Cacheable("departments")
    List<Department> findAll();
}
