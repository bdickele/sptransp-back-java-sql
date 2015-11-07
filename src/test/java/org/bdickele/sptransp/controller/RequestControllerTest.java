package org.bdickele.sptransp.controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import org.bdickele.sptransp.controller.dto.RequestAgreementVisaDTO;
import org.bdickele.sptransp.controller.dto.RequestDTO;
import org.bdickele.sptransp.domain.Request;
import org.bdickele.sptransp.domain.RequestAgreementStatus;
import org.bdickele.sptransp.domain.RequestAgreementVisa;
import org.bdickele.sptransp.domain.RequestAgreementVisaStatus;
import org.bdickele.sptransp.repository.RequestRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.sql.DataSource;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.sql;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Bertrand DICKELE
 */
public class RequestControllerTest extends AbstractControllerTest {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private DataSource dataSource;

    private Long requestId, visaId;


    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        mapper = new ObjectMapper();
        reader = mapper.reader(RequestDTO.class);
    }

    @After
    public void after() {
        if (visaId!=null) {
            new DbSetup(new DataSourceDestination(dataSource), sql("delete from ST_REQUEST_AGR_VISA where ID = " + visaId)).launch();
        }

        if (requestId!=null) {
            new DbSetup(new DataSourceDestination(dataSource), sql("delete from ST_REQUEST where ID = " + requestId)).launch();
        }
    }

    @Test
    public void get_request_by_reference_should_work() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/requests/REFERE0001")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        RequestDTO dto = reader.readValue(result);
        assertThat(dto).isNotNull();
        assertThat(dto.getReference()).isEqualTo("REFERE0001");
        assertThat(dto.getAgreementStatusCode()).isEqualTo("P");

        List<RequestAgreementVisaDTO> visas = dto.getAppliedAgreementVisas();
        assertThat(visas.size()).isGreaterThanOrEqualTo(1);

        RequestAgreementVisaDTO visa = visas.get(0);
        assertThat(visa.getDepartmentCode()).isEqualTo("LAW_COMPLIANCE");
        assertThat(visa.getSeniority()).isEqualTo(60);
    }

    @Test
    public void get_request_by_reference_should_return_404_for_a_non_existing_reference() throws Exception {
        mvc.perform(get("/requests/foobar"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void get_requests_being_validated_should_work() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/requests/beingValidated")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        MappingIterator<RequestDTO> mappingIterator = reader.readValues(result);
        List<RequestDTO> list = mappingIterator.readAll();

        assertThat(list.size()).isGreaterThanOrEqualTo(1);
        assertThat(list).extracting("reference").contains("REFERE0001");
    }

    @Test
    public void get_requests_being_validated_for_a_customer_should_work() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/requests/beingValidated/timulf70")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        MappingIterator<RequestDTO> mappingIterator = reader.readValues(result);
        List<RequestDTO> list = mappingIterator.readAll();

        assertThat(list.size()).isGreaterThanOrEqualTo(1);
        assertThat(list).extracting("reference").contains("REFERE0001");
    }

    @Test
    public void get_requests_validated_or_refused_should_work() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/requests/grantedOrRefused")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        MappingIterator<RequestDTO> mappingIterator = reader.readValues(result);
        List<RequestDTO> list = mappingIterator.readAll();

        assertThat(list.size()).isGreaterThanOrEqualTo(1);
        assertThat(list).extracting("reference").contains("REFERE0002");
    }

    @Test
    public void get_requests_validated_or_refused_for_a_customer_should_work() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/requests/grantedOrRefused/timulf70")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        MappingIterator<RequestDTO> mappingIterator = reader.readValues(result);
        List<RequestDTO> list = mappingIterator.readAll();

        assertThat(list.size()).isGreaterThanOrEqualTo(1);
        assertThat(list).extracting("reference").contains("REFERE0002");
    }

    @Test
    public void insertion_and_update_should_work() throws Exception {
        // Current number of pending requests
        List<Request> customerRequests = requestRepository.findByCustomerUidAndAgreementStatusInOrderByCreationDate("timulf70", RequestAgreementStatus.PENDING);
        int numberBefore = customerRequests.size();

        // ==== INSERTION ====

        MvcResult mvcResult = mvc.perform(post("/requests")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"goodsCode\": \"FOOD\", \"departureCode\": \"MOON\", " +
                        "\"arrivalCode\": \"EARTH\", \"customerUid\": \"timulf70\"}"))
                .andExpect(status().isCreated())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        RequestDTO createdDTO = reader.readValue(result);
        String reference = createdDTO.getReference();
        assertThat(reference).isNotNull();

        customerRequests = requestRepository.findByCustomerUidAndAgreementStatusInOrderByCreationDate("timulf70", RequestAgreementStatus.PENDING);
        int numberAfter = customerRequests.size();

        assertThat(numberAfter).isEqualTo(numberBefore+1);

        Request request = requestRepository.findByReference(reference);
        requestId = request.getId();
        assertThat(request).isNotNull();
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.PENDING);
        assertThat(request.getAgreementVisas().size()).isEqualTo(0);

        // ==== UPDATE ====

        // We apply the first visa required by someone who is from "Law compliance" department with a seniority >= 20
        // whlofu42 is Helen Cox's UID (Law compliance / 60)
        mvc.perform(put("/requests/" + reference)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"employeeUid\": \"whlofu42\", \"statusCode\": \"G\", " +
                        "\"comment\": \"for test\"}"))
                .andExpect(status().isOk())
                .andReturn();

        request = requestRepository.findByReference(reference);
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.PENDING);
        assertThat(request.getAgreementVisas().size()).isEqualTo(1);

        RequestAgreementVisa visa = request.getAgreementVisas().get(0);
        visaId = visa.getId();
        assertThat(visa.getStatus()).isEqualTo(RequestAgreementVisaStatus.GRANTED);
    }
}
