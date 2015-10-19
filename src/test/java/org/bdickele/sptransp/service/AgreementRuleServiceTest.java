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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;

/**
 * Created by Bertrand DICKELE
 */
public class AgreementRuleServiceTest extends AbstractServiceTest {

    public static final Date NOW = new Date();

    public static final Operation TEST_RULE_DELETE = sequenceOf(
            sql("delete from ST_AGR_RULE_VISA_AUD where ID_RULE = 500"),
            sql("delete from ST_AGREEMENT_RULE_AUD where ID_RULE = 500 "),
            sql("delete from ST_AGR_RULE_VISA where ID_RULE = 500"),
            sql("delete from ST_AGREEMENT_RULE where ID = 500 "),
            sql("delete from ST_DESTINATION where ID = 500"));

    public static final Operation TEST_RULE_INSERT = sequenceOf(
            insertInto("ST_DESTINATION")
                    .columns("id", "code", "name")
                    .values(500, "DEATH_STAR", "Death star")
                    .build(),
            insertInto("ST_AGREEMENT_RULE")
                    .columns("id", "version", "id_destination", "id_goods", "req_allowed",
                            "creation_date", "creation_user", "update_date", "update_user")
                    .values(500, 1, 500, 1, false, NOW, "test", NOW, "test")
                    .build(),
            insertInto("ST_AGR_RULE_VISA")
                    .columns("id", "id_rule", "rank", "id_department", "seniority")
                    .values(501, 500, 0, 1, 60)
                    .build(),
            insertInto("ST_AGR_RULE_VISA")
                    .columns("id", "id_rule", "rank", "id_department", "seniority")
                    .values(502, 500, 0, 2, 40)
                    .build());

    @Autowired
    private AgreementRuleService service;

    @Autowired
    private AgreementRuleRepository repository;

    @Autowired
    private DataSource dataSource;


    @After
    public void after() {
        new DbSetup(new DataSourceDestination(dataSource), TEST_RULE_DELETE).launch();
    }

    @Test
    public void update_of_rule_should_work() {
        new DbSetup(new DataSourceDestination(dataSource), sequenceOf(TEST_RULE_DELETE, TEST_RULE_INSERT)).launch();

        AgreementRule rule = repository.findOne(500L);
        assertThat(rule).isNotNull();
        assertThat(rule.getAllowed()).isFalse();

        List<AgreementRuleVisa> visas = rule.getVisas();
        assertThat(visas).extracting("department.code", "seniority.value").containsExactly(
                tuple("LAW_COMPLIANCE", 60),
                tuple("SHUTTLE_COMPLIANCE", 40));

        service.update("DEATH_STAR", "OIL", true, Arrays.asList(
                        Pair.of(DomainTestData.DEPARTMENT_LAW_COMPLIANCE, Seniority.of(50)),
                        Pair.of(DomainTestData.DEPARTMENT_SHUTTLE_COMPLIANCE, Seniority.of(40)),
                        Pair.of(DomainTestData.DEPARTMENT_GOODS_INSPECTION, Seniority.of(30)),
                        Pair.of(DomainTestData.DEPARTMENT_JOURNEY_SUPERVISION, Seniority.of(20))),
                "test");

        rule = repository.findOne(500L);
        assertThat(rule).isNotNull();
        assertThat(rule.getAllowed()).isTrue();

        visas = rule.getVisas();
        assertThat(visas).extracting("department.code", "seniority.value").containsExactly(
                tuple("LAW_COMPLIANCE", 50),
                tuple("SHUTTLE_COMPLIANCE", 40),
                tuple("GOODS_INSPECTION", 30),
                tuple("JOURNEY_SUPERVISION", 20));
    }
}
