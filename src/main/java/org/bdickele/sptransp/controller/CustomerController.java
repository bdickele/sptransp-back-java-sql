package org.bdickele.sptransp.controller;

import org.bdickele.sptransp.controller.dto.CustomerDTO;
import org.bdickele.sptransp.domain.Customer;
import org.bdickele.sptransp.exception.SpTranspBizError;
import org.bdickele.sptransp.repository.CustomerRepository;
import org.bdickele.sptransp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@RestController
@RequestMapping("/customers")
public class CustomerController extends AbstractController {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerService service;


    @RequestMapping(method= RequestMethod.GET,
            produces="application/json")
    public List<CustomerDTO> customers() {
        return CustomerDTO.build(repository.findAllByOrderByFullNameAsc());
    }

    @RequestMapping(value="/{uid}", method= RequestMethod.GET,
            produces="application/json")
    public CustomerDTO customer(@PathVariable String uid) {
        Customer customer = repository.findByUid(uid);
        if (customer==null) {
            throw SpTranspBizError.CUSTOMER_NOT_FOUND.exception(uid);
        }
        return CustomerDTO.build(customer);
    }

    @RequestMapping(
            method= RequestMethod.POST,
            consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO create(@RequestBody CustomerDTO dto) {
        Customer customer = service.create(dto.getFullName(), TEMP_USER_UID);
        return CustomerDTO.build(customer);
    }

    @RequestMapping(
            value="/{uid}",
            method= RequestMethod.PUT,
            consumes="application/json")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO update(@PathVariable String uid, @RequestBody CustomerDTO dto) {
        Customer customer = service.update(uid, dto.getFullName(), TEMP_USER_UID);
        return CustomerDTO.build(customer);
    }
}
