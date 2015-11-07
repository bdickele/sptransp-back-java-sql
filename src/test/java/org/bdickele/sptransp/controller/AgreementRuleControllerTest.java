package org.bdickele.sptransp.controller;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.bdickele.sptransp.controller.dto.AgreementRuleDTO;
import org.bdickele.sptransp.domain.AgreementRule;
import org.bdickele.sptransp.domain.AgreementRuleVisa;
import org.bdickele.sptransp.repository.AgreementRuleRepository;
import org.bdickele.sptransp.service.AgreementRuleServiceIntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.sql.DataSource;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Bertrand DICKELE
 */
public class AgreementRuleControllerTest extends AbstractControllerTest {

    @Autowired
    private AgreementRuleRepository repository;

    @Autowired
    private DataSource dataSource;


    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        mapper = new ObjectMapper();
        reader = mapper.reader(AgreementRuleDTO.class);
    }

    @After
    public void after() {
        deleteTestData();
    }

    private void deleteTestData() {
        AgreementRule rule = repository.findByDestinationCodeAndGoodsCode("DEATH_STAR", "FOOD");
        List<Operation> sqlOperations = AgreementRuleServiceIntegrationTest.buildDeleteOperations(rule == null ? null : rule.getId());
        new DbSetup(new DataSourceDestination(dataSource), sequenceOf(sqlOperations)).launch();
    }

    @Test
    public void all_agreement_rules_should_be_returned_as_json() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/agreementRules"))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        MappingIterator<AgreementRuleDTO> mappingIterator = reader.readValues(result);
        List<AgreementRuleDTO> dtoList = mappingIterator.readAll();

        assertThat(dtoList.size()).isGreaterThanOrEqualTo(5);
        assertThat(dtoList).extracting("destinationCode", "goodsCode").contains(
                tuple("EARTH", "OIL"),
                tuple("EARTH", "FOOD"),
                tuple("EARTH", "WEAPON"),
                tuple("MOON", "OIL"));
    }

    @Test
    public void find_by_destination_and_goods_codes_should_work() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/agreementRules/earth/food")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        AgreementRuleDTO rule = reader.readValue(result);

        assertThat(rule).isNotNull();
        assertThat(rule.getDestinationCode()).isEqualTo("EARTH");
        assertThat(rule.getGoodsCode()).isEqualTo("FOOD");
    }

    @Test
    public void find_by_destination_and_goods_codes_should_return_404_when_incorrect_codes() throws Exception {
        mvc.perform(get("/agreementRules/foo/bar"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void insertion_and_update_of_rule_should_work() throws Exception {
        deleteTestData();
        new DbSetup(new DataSourceDestination(dataSource), AgreementRuleServiceIntegrationTest.TEST_DESTINATION_INSERT).launch();

        // ==== INSERTION ====

        mvc.perform(post("/agreementRules/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"destinationCode\": \"DEATH_STAR\", \"goodsCode\": \"FOOD\", \"reqAllowed\": false, " +
                        "\"agreementVisas\": [{\"departmentCode\": \"LAW_COMPLIANCE\", \"seniority\": 20}]}"))
                .andExpect(status().isCreated())
                .andReturn();

        AgreementRule rule = repository.findByDestinationCodeAndGoodsCode("DEATH_STAR", "FOOD");
        assertThat(rule.getAllowed()).isFalse();

        Long ruleId = rule.getId();

        List<AgreementRuleVisa> visas = rule.getVisas();
        assertThat(visas.size()).isEqualTo(1);
        assertThat(visas).extracting("department.code", "seniority.value").containsExactly(
                tuple("LAW_COMPLIANCE", 20));

        // ==== UPDATE ====

        mvc.perform(put("/agreementRules/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"destinationCode\": \"DEATH_STAR\", \"goodsCode\": \"FOOD\", \"reqAllowed\": true, " +
                        "\"agreementVisas\": [" +
                            "{\"departmentCode\": \"LAW_COMPLIANCE\", \"seniority\": 50}, " +
                            "{\"departmentCode\": \"SHUTTLE_COMPLIANCE\", \"seniority\": 40}]}"))
                .andExpect(status().isOk())
                .andReturn();

        rule = repository.findOne(ruleId);
        assertThat(rule).isNotNull();
        assertThat(rule.getAllowed()).isTrue();

        visas = rule.getVisas();
        assertThat(visas.size()).isEqualTo(2);
        assertThat(visas).extracting("department.code", "seniority.value").containsExactly(
                tuple("LAW_COMPLIANCE", 50),
                tuple("SHUTTLE_COMPLIANCE", 40));

        // ==== UPDATE : visas removed and request not allowed anymore ====

        mvc.perform(put("/agreementRules/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"destinationCode\": \"DEATH_STAR\", \"goodsCode\": \"FOOD\", \"reqAllowed\": false}"))
                .andExpect(status().isOk())
                .andReturn();

        rule = repository.findOne(ruleId);
        assertThat(rule).isNotNull();
        assertThat(rule.getAllowed()).isFalse();
        assertThat(rule.getVisas().size()).isEqualTo(0);
    }
}
