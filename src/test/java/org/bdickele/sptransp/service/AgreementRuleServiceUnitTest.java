package org.bdickele.sptransp.service;

import org.apache.commons.lang3.tuple.Pair;
import org.bdickele.sptransp.domain.AgreementRule;
import org.bdickele.sptransp.domain.AgreementRuleVisa;
import org.bdickele.sptransp.domain.Seniority;
import org.bdickele.sptransp.repository.AgreementRuleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;
import static org.bdickele.sptransp.domain.DomainTestData.*;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Bertrand DICKELE
 */
public class AgreementRuleServiceUnitTest {

    @InjectMocks private AgreementRuleService service = new AgreementRuleService();

    @Mock private AgreementRuleRepository repository;

    @Mock private EntityManager entityManager;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        //doNothing().when(entityManager.persist(any()));
    }

    @Test
    public void update_of_rule_should_work() {
        // Given
        AgreementRule rule = AgreementRule.build(-1L, DESTINATION_EARTH, GOODS_FOOD, true, USER_UID)
                .addVisa(-10L, DEPARTMENT_SHUTTLE_COMPLIANCE, SENIORITY_50);

        // When
        when(repository.findByDestinationCodeAndGoodsCode(anyString(), anyString())).thenReturn(rule);
        rule = service.update(DESTINATION_EARTH.getCode(), GOODS_FOOD.getCode(), false,
                Arrays.asList(
                        Pair.of(DEPARTMENT_GOODS_INSPECTION, Seniority.of(25)),
                        Pair.of(DEPARTMENT_JOURNEY_SUPERVISION, Seniority.of(65))),
                "FOO");
        // Then
        assertThat(rule.getAllowed()).isFalse();
        List<AgreementRuleVisa> visas = rule.getVisas();
        assertThat(visas.size()).isEqualTo(2);
        assertThat(visas).extracting("department.code", "seniority.value").containsExactly(
                tuple("GOODS_INSPECTION", 25),
                tuple("JOURNEY_SUPERVISION", 65));
    }
}
