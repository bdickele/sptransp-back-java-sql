package org.bdickele.sptransp.domain.converter;

import org.bdickele.sptransp.domain.RequestAgreementStatus;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class RequestAgreementStatusConverterTest {

    private final RequestAgreementStatusConverter converter = new RequestAgreementStatusConverter();

    @Test
    public void should_convert_from_code_to_enum() {
        assertThat(converter.convertToEntityAttribute("P")).isEqualTo(RequestAgreementStatus.PENDING);
    }

    @Test
    public void should_convert_from_enum_to_code() {
        assertThat(converter.convertToDatabaseColumn(RequestAgreementStatus.REFUSED)).isEqualTo("R");
    }
}
