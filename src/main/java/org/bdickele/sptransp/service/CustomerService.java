package org.bdickele.sptransp.service;

import org.bdickele.sptransp.domain.Customer;
import org.bdickele.sptransp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;

/**
 * Created by Bertrand DICKELE
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CustomerService extends AbstractService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional(propagation = Propagation.REQUIRED)
    @RolesAllowed("ROLE_CUSTOMER")
    public Long create(String uid, String fullName, String creationUserUid) {
        //TODO Generation UID et suppression de l'uid des parametres de la methode
        Customer customer = Customer.build(null, uid, fullName, creationUserUid, passwordEncoder);
        em().persist(customer);
        return customer.getId();
    }
}
