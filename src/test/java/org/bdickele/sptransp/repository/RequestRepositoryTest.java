package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Request;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class RequestRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private RequestRepository repository;


    @Test
    public void find_by_reference_should_work() {
        String reference = repository.findAll().get(0).getReference();
        Request request  = repository.findByReference(reference);
        assertThat(request).isNotNull();
    }
}
