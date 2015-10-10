package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by bdickele
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByOrderByFullNameAsc();

    Customer findByUid(String uid);
}
