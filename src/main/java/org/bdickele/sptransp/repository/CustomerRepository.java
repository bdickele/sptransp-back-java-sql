package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bdickele
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
