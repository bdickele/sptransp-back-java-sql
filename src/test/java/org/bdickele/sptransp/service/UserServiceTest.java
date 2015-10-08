package org.bdickele.sptransp.service;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
@RunWith(JUnitParamsRunner.class)
public class UserServiceTest {

    private static List<String> UIDS = Arrays.asList("john1", "john2", "john3", "johndoe4");


    @Test
    public void suffix_extracting_should_work() {
        String prefix = "john";
        Optional<Integer> result = UserService.extractSuffixNumberFromUid("johndoe12", prefix);
        assertThat(result).isEmpty();

        result = UserService.extractSuffixNumberFromUid("john12", prefix);
        assertThat(result).contains(12);
    }

    public Object[][] indexExtractionProvider() {
        return new Object[][] {
                {UIDS, "john", Optional.of(3)},
                {UIDS, "johndoe", Optional.of(4)},
                {UIDS, "johndoee", Optional.empty()},
                {UIDS, "joh", Optional.empty()}
        };
    }

    @Test
    @Parameters(method = "indexExtractionProvider")
    public void get_max_index_of_suffixes_should_return_a_value(List<String> uids, String prefix,
                                                                Optional<Integer> expectedResult) {
        Optional<Integer> max = UserService.getMaxIndexOfUidsWithSamePrefix(uids, prefix);
        assertThat(max).isEqualTo(expectedResult);
    }
}
