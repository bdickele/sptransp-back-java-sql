package org.bdickele.sptransp.service;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.bdickele.sptransp.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Bertrand DICKELE
 */
@RunWith(JUnitParamsRunner.class)
public class UserServiceTest {

    private static List<String> UIDS = Arrays.asList("john1", "john2", "john3", "johndoe4");

    @Mock private UserRepository repository;

    @InjectMocks private UserService service;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void creation_of_uid_should_work() {
        when(repository.findUidsStartingWith(anyString())).thenReturn(
                Arrays.asList("johnny1", "johnny2", "johnny3", "john4"));
        String result = service.generateUid("johnny");
        assertThat(result).isEqualTo("johnny4");

        // Only the 6 first charaters count
        result = service.generateUid("johnnydoe");
        assertThat(result).isEqualTo("johnny4");

        result = service.generateUid("john");
        assertThat(result).isEqualTo("john5");
    }

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
