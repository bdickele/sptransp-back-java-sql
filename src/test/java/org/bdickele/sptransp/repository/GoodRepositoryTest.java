package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Good;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;

/**
 * Created by Bertrand DICKELE
 */
public class GoodRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private GoodRepository repository;


    @Test
    public void should_find_all_goods() {
        List<Good> goods = repository.findAll();
        assertThat(goods).hasSize(5);

        assertThat(goods).extracting("name", "code3").containsExactly(
                tuple("Oil", "OIL"),
                tuple("Food", "FOO"),
                tuple("Machine tool", "MAT"),
                tuple("Weapon", "WEA"),
                tuple("Medicine", "MED"));
    }
}
