package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bdickele
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
