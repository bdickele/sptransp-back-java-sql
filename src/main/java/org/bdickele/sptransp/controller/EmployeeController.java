package org.bdickele.sptransp.controller;

import org.bdickele.sptransp.controller.dto.EmployeeDTO;
import org.bdickele.sptransp.domain.Employee;
import org.bdickele.sptransp.repository.EmployeeRepository;
import org.bdickele.sptransp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeService service;


    @RequestMapping(method= RequestMethod.GET,
            produces="application/json")
    public List<EmployeeDTO> employees() {
        return EmployeeDTO.build(repository.findAllByOrderByFullNameAsc());
    }

    @RequestMapping(
            method= RequestMethod.PUT,
            consumes="application/json")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO dto, Principal principal) {
        Employee employee = service.update(dto.getUid(), dto.getFullName(), dto.getProfileCode(),
                dto.getDepartmentCode(), dto.getSeniority(), principal.getName());
        return EmployeeDTO.build(employee);
    }
}
