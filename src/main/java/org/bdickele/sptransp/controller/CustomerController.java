package org.bdickele.sptransp.controller;

import org.bdickele.sptransp.controller.dto.CustomerDTO;
import org.bdickele.sptransp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;


    @RequestMapping(method= RequestMethod.GET,
            produces="application/json")
    public List<CustomerDTO> customers() {
        return CustomerDTO.build(repository.findAllByOrderByFullNameAsc());
    }
}
