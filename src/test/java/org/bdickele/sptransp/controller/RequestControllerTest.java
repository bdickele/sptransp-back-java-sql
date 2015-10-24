package org.bdickele.sptransp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bdickele.sptransp.controller.dto.RequestDTO;
import org.bdickele.sptransp.domain.Customer;
import org.bdickele.sptransp.domain.Request;
import org.bdickele.sptransp.domain.RequestAgreementStatus;
import org.bdickele.sptransp.repository.CustomerRepository;
import org.bdickele.sptransp.repository.RequestRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Bertrand DICKELE
 */
public class RequestControllerTest extends AbstractControllerTest {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        mapper = new ObjectMapper();
        reader = mapper.reader(RequestDTO.class);
    }

    @Test
    public void insertion_should_work() throws Exception {
        // Current number of pending requests
        Customer customer = customerRepository.findByUid("timulf70");
        List<Request> customerRequests = requestRepository.findByCustomerAndAgreementStatus(customer, RequestAgreementStatus.PENDING);
        int numberBefore = customerRequests.size();

        MvcResult mvcResult = mvc.perform(post("/requests")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"goodsCode\": \"FOOD\", \"departureCode\": \"MOON\", " +
                        "\"arrivalCode\": \"EARTH\", \"customerUid\": \"timulf70\"}"))
                .andExpect(status().isCreated())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        RequestDTO createdDTO = reader.readValue(result);

        assertThat(createdDTO.getReference()).isNotNull();

        customerRequests = requestRepository.findByCustomerAndAgreementStatus(customer, RequestAgreementStatus.PENDING);
        int numberAfter = customerRequests.size();

        assertThat(numberAfter).isEqualTo(numberBefore+1);
    }
}
