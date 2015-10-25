package org.bdickele.sptransp.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bertrand DICKELE
 */
public class RequestAgreementStatusTest {

    @Test
    public void database_codes_should_be_unique() {
        Set<String> codes = new HashSet<>();

        for (RequestAgreementStatus status: RequestAgreementStatus.values()) {
            String code = status.getCode();
            if (codes.contains(code)) {
                Assert.fail("Database codes of RequestAgreementStatus are not unique, fix it ASAP");
            }
            codes.add(code);
        }
    }
}
