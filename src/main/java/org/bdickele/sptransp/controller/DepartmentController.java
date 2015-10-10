package org.bdickele.sptransp.controller;

import org.bdickele.sptransp.controller.dto.DepartmentDTO;
import org.bdickele.sptransp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController extends AbstractController {

    @Autowired
    private DepartmentRepository repository;

    @RequestMapping(method= RequestMethod.GET,
            produces="application/json")
    public List<DepartmentDTO> departments() {
        return DepartmentDTO.build(repository.findAllByOrderByNameAsc());
    }
}
