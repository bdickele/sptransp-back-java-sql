package org.bdickele.sptransp.controller;

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
        MvcResult mvcResult = mvc.perform(get("/requests/DBSKRQ8415")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        RequestDTO dto = reader.readValue(result);
        assertThat(dto).isNotNull();
        assertThat(dto.getReference()).isEqualTo("DBSKRQ8415");
        assertThat(dto.getAgreementStatusCode()).isEqualTo("P");

        List<RequestAgreementVisaDTO> visas = dto.getAppliedAgreementVisas();
        assertThat(visas.size()).isEqualTo(3);
    }

    @Test
    public void get_request_by_reference_should_return_404_for_a_non_existing_reference() throws Exception {
        mvc.perform(get("/requests/foobar"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    // On pourrait vouloir utiliser JUnitParams pour ces 4 tests, malheureusement ce n'est pas possible
    // car le runner JUnit desactiverait le runner SpringJUnit

    @Test
    public void get_requests_being_validated_should_work() throws Exception {
        get_requests_should_work("/requests/beingValidated", "DBSKRQ8415");
    }

    @Test
    public void get_requests_being_validated_for_a_customer_should_work() throws Exception {
        get_requests_should_work("/requests/beingValidated/pfdxtv63", "DBSKRQ8415");
    }

    @Test
    public void get_requests_granted_or_refused_should_work() throws Exception {
        get_requests_should_work("/requests/grantedOrRefused", "CSVVCZ6671");
    }

    @Test
    public void get_requests_granted_or_refused_for_a_customer_should_work() throws Exception {
        get_requests_should_work("/requests/grantedOrRefused/timulf70", "EWDNDE0601");
    }

    private void get_requests_should_work(String url, String reference) throws Exception {
        MvcResult mvcResult = mvc.perform(get(url + "?size=100")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Probleme: maintenant qu'on a implemente la pagination, on ne peut pas recuperer
        // le MappingIterator aussi simplement, car dans le resultat c'est qu'on n'a pas
        // directement le tableau, mais une variable "content". Je me content de recuperer
        // la premiere valeur.

        byte[] result = mvcResult.getResponse().getContentAsByteArray();
        RequestDTO dto = reader.readValue(result, 12, 999999999);

        assertThat(dto).isNotNull();
        assertThat(dto.getReference()).isEqualTo(reference);
    }

    @Test
    public void insertion_and_update_should_work() throws Exception {

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

        Request request = requestRepository.findByReference(reference);
        requestId = request.getId();
        assertThat(request).isNotNull();
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.PENDING);
        assertThat(request.getAgreementVisas().size()).isEqualTo(0);

        // ==== UPDATE ====

        // We apply the first visa required by someone who is from "Law compliance" department with a sufficient seniority
        mvc.perform(put("/requests/" + reference)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"employeeUid\": \"qlomny06\", \"statusCode\": \"G\", " +
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
