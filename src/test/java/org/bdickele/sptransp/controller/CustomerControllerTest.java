package org.bdickele.sptransp.controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import org.bdickele.sptransp.controller.dto.CustomerDTO;
import org.bdickele.sptransp.domain.Customer;
import org.bdickele.sptransp.repository.CustomerRepository;
import org.bdickele.sptransp.service.CustomerServiceIntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Bertrand DICKELE
 */
public class CustomerControllerTest extends AbstractControllerTest {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private DataSource dataSource;


    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        mapper = new ObjectMapper();
        reader = mapper.reader(CustomerDTO.class);
    }

    @After
    public void after() {
        new DbSetup(new DataSourceDestination(dataSource), CustomerServiceIntegrationTest.TEST_CUSTOMER_DELETE).launch();
    }

    @Test
    public void get_customer_should_work_for_an_existing_employee() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/customers/timulf70"))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        CustomerDTO customer = reader.readValue(result);
        assertThat(customer).isNotNull();
        assertThat(customer.getFullName()).isEqualTo("Jabberstorm");
    }

    @Test
    public void get_customer_should_return_404_for_a_non_existing_employee() throws Exception {
        mvc.perform(get("/customers/foobar"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void all_customers_should_be_returned_as_json() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        MappingIterator<CustomerDTO> mappingIterator = reader.readValues(result);
        List<CustomerDTO> dtoList = mappingIterator.readAll();

        assertThat(dtoList.size()).isGreaterThanOrEqualTo(5);
        assertThat(dtoList).extracting("uid", "fullName").contains(
                tuple("timulf70", "Jabberstorm"),
                tuple("bxcegf67", "Quamba"));
    }

    @Test
    public void insertion_and_update_should_work() throws Exception {
        new DbSetup(new DataSourceDestination(dataSource), CustomerServiceIntegrationTest.TEST_CUSTOMER_DELETE).launch();

        // ==== INSERTION ===

        MvcResult mvcResult = mvc.perform(post("/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"fullName\": \"TESTCU_NAME\"}"))
                .andExpect(status().isCreated())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        CustomerDTO dto = reader.readValue(result);

        String uid = dto.getUid();

        assertThat(uid).isNotNull();

        // ==== UPDATE ===

        mvc.perform(put("/customers/" + uid)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"fullName\": \"TESTCU_NAME 2\"}"))
                .andExpect(status().isOk())
                .andReturn();

        Customer customer = repository.findByUid(uid);
        assertThat(customer.getFullName()).isEqualTo("TESTCU_NAME 2");
        assertThat(customer.getVersion()).isEqualTo(2);
    }
}
