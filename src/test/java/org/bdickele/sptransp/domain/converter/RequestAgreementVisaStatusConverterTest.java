package org.bdickele.sptransp.domain.converter;

import org.bdickele.sptransp.domain.RequestAgreementVisaStatus;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class RequestAgreementVisaStatusConverterTest {

    private final AppliedAgreementVisaStatusConverter converter = new AppliedAgreementVisaStatusConverter();

    @Test
    public void should_convert_from_code_to_enum() {
        assertThat(converter.convertToEntityAttribute("D")).isEqualTo(RequestAgreementVisaStatus.DENIED);
    }

    @Test
    public void should_convert_from_enum_to_code() {
        assertThat(converter.convertToDatabaseColumn(RequestAgreementVisaStatus.GRANTED)).isEqualTo("G");
    }
}
