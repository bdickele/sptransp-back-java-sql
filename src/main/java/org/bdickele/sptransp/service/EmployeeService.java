package org.bdickele.sptransp.service;

import org.bdickele.sptransp.configuration.DomainCacheConfig;
import org.bdickele.sptransp.domain.Department;
import org.bdickele.sptransp.domain.Employee;
import org.bdickele.sptransp.domain.Seniority;
import org.bdickele.sptransp.domain.UserProfile;
import org.bdickele.sptransp.repository.DepartmentRepository;
import org.bdickele.sptransp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bertrand DICKELE
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class EmployeeService extends AbstractService {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional(propagation = Propagation.REQUIRED)
    @CacheEvict(DomainCacheConfig.EMPLOYEES)
    public Employee create(String fullName, String profileCode, String departmentCode,
                           Integer seniority, String creationUser) {
        String uid = userService.generateUid(fullName);
        Department department = departmentRepository.findByCode(departmentCode);

        Employee employee = Employee.build(null, uid, fullName, UserProfile.getByCode(profileCode),
                department, new Seniority(seniority), creationUser, passwordEncoder);

        em().persist(employee);
        return employee;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @CacheEvict(DomainCacheConfig.EMPLOYEES)
    public Employee update(String uid, String fullName, String profileCode, String departmentCode,
                           Integer seniority, String updateUser) {
        // UID cannot be updated : we use it to retrieve current employee
        Employee employee = employeeRepository.findByUid(uid);
        Department department = departmentRepository.findByCode(departmentCode);

        employee.update(fullName, UserProfile.getByCode(profileCode), department,
                new Seniority(seniority), updateUser);

        return employee;
    }
}
