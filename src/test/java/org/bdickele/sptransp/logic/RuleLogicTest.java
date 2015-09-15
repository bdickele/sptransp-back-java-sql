package org.bdickele.sptransp.logic;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.bdickele.sptransp.domain.AgreementRule;
import org.bdickele.sptransp.domain.Destination;
import org.bdickele.sptransp.domain.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.bdickele.sptransp.domain.DomainTestData.*;

/**
 * Created by Bertrand DICKELE
 */
@RunWith(JUnitParamsRunner.class)
public class RuleLogicTest {

    private static final AgreementRule RULE_MOON_OIL = buildRule(1L, DESTINATION_MOON, GOODS_OIL);
    private static final AgreementRule RULE_MOON_FOOD = buildRule(2L, DESTINATION_MOON, GOODS_FOOD);
    private static final AgreementRule RULE_MARS_OIL = buildRule(3L, DESTINATION_MARS, GOODS_OIL);
    private static final AgreementRule RULE_MARS_MEDICINE = buildRule(4L, DESTINATION_MARS, GOODS_MEDICINE);

    private static final List<AgreementRule> RULES = Arrays.asList(RULE_MOON_OIL, RULE_MOON_FOOD, RULE_MARS_OIL, RULE_MARS_MEDICINE);


    private Object[] testValues() {
        return new Object[]{
                new Object[] {DESTINATION_MOON, GOODS_OIL, Optional.of(RULE_MOON_OIL)},
                new Object[] {DESTINATION_MOON, GOODS_FOOD, Optional.of(RULE_MOON_FOOD)},
                new Object[] {DESTINATION_MARS, GOODS_OIL, Optional.of(RULE_MARS_OIL)},
                new Object[] {DESTINATION_MARS, GOODS_MEDICINE, Optional.of(RULE_MARS_MEDICINE)},
                new Object[] {DESTINATION_MARS, GOODS_FOOD, Optional.empty()}
        };
    }

    @Test
    @Parameters(method="testValues")
    public void should_find_the_appropriate_rule(Destination destination, Goods goods, Optional<AgreementRule> expectedResult) {
        Optional<AgreementRule> actualResult = RuleLogic.findRuleForDestinationAndGood(RULES, destination.getId(), goods.getId());
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
