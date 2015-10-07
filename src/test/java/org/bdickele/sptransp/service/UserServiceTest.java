package org.bdickele.sptransp.service;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class UserServiceTest {

    @Test
    public void suffix_extracting_should_work() {
        String prefix = "john";
        Optional<Integer> result = UserService.extractSuffixNumberFromUid("johndoe12", prefix);
        assertThat(result).isEmpty();

        result = UserService.extractSuffixNumberFromUid("john12", prefix);
        assertThat(result).contains(12);
    }
}
