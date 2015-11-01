package org.bdickele.sptransp.service;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.apache.commons.lang3.tuple.Pair;
import org.bdickele.sptransp.domain.AgreementRule;
import org.bdickele.sptransp.domain.AgreementRuleVisa;
import org.bdickele.sptransp.domain.DomainTestData;
import org.bdickele.sptransp.domain.Seniority;
import org.bdickele.sptransp.repository.AgreementRuleRepository;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;

/**
 * Created by Bertrand DICKELE
 */
public class AgreementRuleServiceIntegrationTest extends AbstractServiceIntegrationTest {

    public static final Operation TEST_DESTINATION_DELETE = sql("delete from ST_DESTINATION where CODE = 'DEATH_STAR'");

    public static final Operation TEST_DESTINATION_INSERT = insertInto("ST_DESTINATION")
            .columns("id", "code", "name")
            .values(500, "DEATH_STAR", "Death star")
            .build();

    @Autowired
    private AgreementRuleService service;

    @Autowired
    private AgreementRuleRepository repository;

    @Autowired
    private DataSource dataSource;


    @After
    public void after() {
        deleteTestData();
    }

    private void deleteTestData() {
        AgreementRule rule = repository.findByDestinationCodeAndGoodsCode("DEATH_STAR", "FOOD");
        List<Operation> sqlOperations = buildDeleteOperations(rule==null ? null : rule.getId());
        new DbSetup(new DataSourceDestination(dataSource), sequenceOf(sqlOperations)).launch();
    }

    public static List<Operation> buildDeleteOperations(Long ruleId) {
        List<Operation> sqlOperations = new ArrayList<>();

        if (ruleId!=null) {
            sqlOperations.add(sql("delete from ST_AGR_RULE_VISA_AUD where ID_RULE = " + ruleId));
            sqlOperations.add(sql("delete from ST_AGREEMENT_RULE_AUD where ID_RULE = " + ruleId));
            sqlOperations.add(sql("delete from ST_AGR_RULE_VISA where ID_RULE = " + ruleId));
            sqlOperations.add(sql("delete from ST_AGREEMENT_RULE where ID = " + ruleId));
        }

        sqlOperations.add(TEST_DESTINATION_DELETE);

        return sqlOperations;
    }

    @Test
    public void insertion_and_update_of_rule_should_work() {
        deleteTestData();
        new DbSetup(new DataSourceDestination(dataSource), TEST_DESTINATION_INSERT).launch();

        // ==== INSERTION ====

        service.create("DEATH_STAR", "FOOD", true,
                Arrays.asList(Pair.of(DomainTestData.DEPARTMENT_LAW_COMPLIANCE, Seniority.of(20))),
                "testuser");

        AgreementRule rule = repository.findByDestinationCodeAndGoodsCode("DEATH_STAR", "FOOD");

        Long ruleId = rule.getId();

        List<AgreementRuleVisa> visas = rule.getVisas();
        assertThat(visas.size()).isEqualTo(1);
        assertThat(visas).extracting("department.code", "seniority.value").containsExactly(
                tuple("LAW_COMPLIANCE", 20));

        // ==== UPDATE ====

        service.update("DEATH_STAR", "FOOD", true, Arrays.asList(
                        Pair.of(DomainTestData.DEPARTMENT_LAW_COMPLIANCE, Seniority.of(50)),
                        Pair.of(DomainTestData.DEPARTMENT_SHUTTLE_COMPLIANCE, Seniority.of(40))),
                        "test");

        rule = repository.findOne(ruleId);
        assertThat(rule).isNotNull();
        assertThat(rule.getAllowed()).isTrue();

        visas = rule.getVisas();
        assertThat(visas.size()).isEqualTo(2);
        assertThat(visas).extracting("department.code", "seniority.value").containsExactly(
                tuple("LAW_COMPLIANCE", 50),
                tuple("SHUTTLE_COMPLIANCE", 40));
    }
}
