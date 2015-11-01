package org.bdickele.sptransp.domain.converter;

import org.bdickele.sptransp.domain.RequestAgreementVisaStatus;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class AppliedAgreementVisaStatusConverterTest {

    private AppliedAgreementVisaStatusConverter converter = new AppliedAgreementVisaStatusConverter();


    @Test
    public void should_convert_from_status_to_string() {
        String code = converter.convertToDatabaseColumn(RequestAgreementVisaStatus.GRANTED);
        assertThat(code).isEqualTo("G");
    }

    @Test
    public void should_convert_from_null_status_to_null() {
        String code = converter.convertToDatabaseColumn(null);
        assertThat(code).isNull();
    }

    @Test
    public void should_convert_from_string_to_status_enum() {
        RequestAgreementVisaStatus status = converter.convertToEntityAttribute("D");
        assertThat(status).isEqualTo(RequestAgreementVisaStatus.DENIED);
    }

    @Test
    public void should_convert_from_null_string_to_null() {
        RequestAgreementVisaStatus status = converter.convertToEntityAttribute(null);
        assertThat(status).isNull();
    }
}
