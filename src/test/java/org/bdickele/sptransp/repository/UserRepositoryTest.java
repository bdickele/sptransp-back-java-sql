package org.bdickele.sptransp.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class UserRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private UserRepository repository;


    @Test
    public void find_by_uid_prefix_should_work() {
        List<String> uids = repository.findUidsStartingWith("x");
        assertThat(uids.size()).isGreaterThanOrEqualTo(2);
        assertThat(uids).contains("xhtqyi65", "xzjwsm38");
    }
}
