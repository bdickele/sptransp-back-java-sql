package org.bdickele.sptransp.service;

import org.bdickele.sptransp.domain.Customer;
import org.bdickele.sptransp.domain.Request;
import org.bdickele.sptransp.domain.RequestAgreementStatus;
import org.bdickele.sptransp.repository.CustomerRepository;
import org.bdickele.sptransp.repository.RequestRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class RequestServiceTest extends AbstractServiceTest {

    @Autowired
    private RequestService service;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    public void creation_of_a_request_should_work() {
        // Current number of pending requests
        Customer customer = customerRepository.findByUid("timulf70");
        List<Request> customerRequests = requestRepository.findByCustomerAndAgreementStatus(customer, RequestAgreementStatus.PENDING);
        int numberBefore = customerRequests.size();

        Request request = service.create("FOOD", "MOON", "EARTH", "timulf70");
        assertThat(request.getReference()).isNotNull();

        customerRequests = requestRepository.findByCustomerAndAgreementStatus(customer, RequestAgreementStatus.PENDING);
        int numberAfter = customerRequests.size();

        assertThat(numberAfter).isEqualTo(numberBefore+1);
    }
}
