package org.bdickele.sptransp.service;

import org.bdickele.sptransp.domain.Department;
import org.bdickele.sptransp.domain.Employee;
import org.bdickele.sptransp.domain.Seniority;
import org.bdickele.sptransp.repository.DepartmentRepository;
import org.bdickele.sptransp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by Bertrand DICKELE
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class EmployeeService extends AbstractService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    public Employee update(String uid, String departmentCode, int seniority, String updateUser) {
        // UID cannot be updated : we use it to retrieve current employee
        Employee employee = employeeRepository.findByUid(uid);
        Department department = departmentRepository.findByCode(departmentCode);

        employee.setDepartment(department);
        employee.setSeniority(new Seniority(seniority));
        employee.setUpdateUser(updateUser);
        employee.setUpdateDate(LocalDateTime.now());

        return employee;
    }
}
