package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Customer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;

/**
 * Created by Bertrand DICKELE
 */
public class CustomerRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private CustomerRepository repository;


    @Test
    public void findAll_should_work() {
        List<Customer> customers = repository.findAll();
        assertThat(customers).extracting("uid", "fullName").contains(
                tuple("timulf70", "Jabberstorm"),
                tuple("bxcegf67", "Quamba"));
    }

    @Test
    public void find_by_id_should_work() {
        Customer customer = repository.findOne(99L);
        assertThat(customer).isNull();

        customer = repository.findOne(31L);
        assertThat(customer).isNotNull();
        assertThat(customer.getUid()).isEqualTo("timulf70");
    }
}
