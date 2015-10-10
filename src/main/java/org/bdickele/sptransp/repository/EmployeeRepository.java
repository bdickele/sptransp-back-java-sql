package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByOrderByFullNameAsc();

    Employee findByUid(String uid);
}
