package org.bdickele.sptransp.controller;

import org.bdickele.sptransp.controller.dto.EmployeeDTO;
import org.bdickele.sptransp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;


    @RequestMapping(path="/employees",
            method= RequestMethod.GET,
            produces="application/json")
    public List<EmployeeDTO> employees() {
        return EmployeeDTO.build(repository.findAllByOrderByFullNameAsc());
    }
}
