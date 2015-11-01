package org.bdickele.sptransp.domain.converter;

import org.bdickele.sptransp.domain.RequestAgreementVisaStatus;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class RequestAgreementVisaStatusConverterTest {

    private final AppliedAgreementVisaStatusConverter converter = new AppliedAgreementVisaStatusConverter();

    @Test
    public void should_convert_from_code_to_enum() {
        RequestAgreementVisaStatus status = converter.convertToEntityAttribute(null);
        assertThat(status).isEqualTo(null);

        status = converter.convertToEntityAttribute("D");
        assertThat(status).isEqualTo(RequestAgreementVisaStatus.DENIED);
    }

    @Test
    public void should_convert_from_enum_to_code() {
        String code = converter.convertToDatabaseColumn(RequestAgreementVisaStatus.GRANTED);
        assertThat(code).isEqualTo("G");
    }
}
