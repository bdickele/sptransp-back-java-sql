package org.bdickele.sptransp.exception;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bertrand DICKELE
 */
public class SpTranspBizErrorTest {

    @Test
    public void error_codes_should_be_unique() {
        Set<Integer> codes = new HashSet<>();

        for (SpTranspBizError error: SpTranspBizError.values()) {
            Integer errorCode = error.getErrorCode();
            if (codes.contains(errorCode)) {
                Assert.fail("Error codes of SpTranspBizError are not unique, fix it ASAP");
            }
            codes.add(errorCode);
        }
    }
}
