package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Destination;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Created by Bertrand DICKELE
 */
public class DestinationRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private DestinationRepository repository;


    @Test
    public void should_find_all_destinations() {
        List<Destination> destinations = repository.findAll();
        assertThat(destinations).hasSize(3);

        assertThat(destinations).extracting("name", "code3").containsExactly(
                tuple("Moon", "MOO"),
                tuple("Mars", "MAR"),
                tuple("Titan", "TIT"));
    }

    @Test
    public void first_destination_should_be_moon() {
        Destination moon = repository.findByCode3("MOO");
        assertThat(moon).isNotNull();
        assertThat(moon.getCode3()).isEqualTo("MOO");
        assertThat(moon.getName()).isEqualTo("Moon");
    }
}
