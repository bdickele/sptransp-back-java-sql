package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Goods;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;

/**
 * Created by Bertrand DICKELE
 */
public class GoodsRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private GoodRepository repository;


    @Test
    public void should_find_all_goods() {
        List<Goods> goods = repository.findAll();
        assertThat(goods).hasSize(5);

        assertThat(goods).extracting("name", "code").containsExactly(
                tuple("Oil", "OIL"),
                tuple("Food", "FOOD"),
                tuple("Machine tool", "MACHINE_TOOL"),
                tuple("Weapon", "WEAPON"),
                tuple("Medicine", "MEDICINE"));
    }
}
