package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.AgreementRule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;

/**
 * Created by Bertrand DICKELE
 */
public class AgreementRuleRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private AgreementRuleRepository repository;


    @Test
    public void findAll_should_work() {
        List<AgreementRule> rules = repository.findAll();
        assertThat(rules).extracting("destination.code", "goods.code").contains(
                tuple("EARTH", "FOOD"),
                tuple("MOON", "OIL"));
    }

    @Test
    public void find_by_destination_code_and_goods_code_should_work() {
        AgreementRule rule = repository.findByDestinationCodeAndGoodsCode("foo", "bar");
        assertThat(rule).isNull();

        rule = repository.findByDestinationCodeAndGoodsCode("EARTH", "FOOD");
        assertThat(rule).isNotNull();
        assertThat(rule.getDestination().getCode()).isEqualTo("EARTH");
    }
}
