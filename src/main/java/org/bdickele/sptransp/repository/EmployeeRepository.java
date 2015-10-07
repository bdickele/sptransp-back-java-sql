package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.configuration.DomainCacheConfig;
import org.bdickele.sptransp.domain.Employee;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Cacheable(DomainCacheConfig.EMPLOYEES)
    List<Employee> findAllByOrderByFullNameAsc();

    Employee findByUid(String uid);
}
