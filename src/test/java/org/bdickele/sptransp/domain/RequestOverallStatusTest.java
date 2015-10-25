package org.bdickele.sptransp.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bertrand DICKELE
 */
public class RequestOverallStatusTest {

    @Test
    public void database_codes_should_be_unique() {
        Set<String> codes = new HashSet<>();

        for (RequestOverallStatus status: RequestOverallStatus.values()) {
            String code = status.getCode();
            if (codes.contains(code)) {
                Assert.fail("Database codes of RequestOverallStatus are not unique, fix it ASAP");
            }
            codes.add(code);
        }
    }
}
