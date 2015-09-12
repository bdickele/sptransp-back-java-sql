package org.bdickele.sptransp.domain.converter;

import org.bdickele.sptransp.domain.AgreementAppliedVisaStatus;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class AgreementAppliedVisaStatusConverterTest {

    private final AgreementAppliedVisaStatusConverter converter = new AgreementAppliedVisaStatusConverter();

    @Test
    public void should_convert_from_code_to_enum() {
        assertThat(converter.convertToEntityAttribute("D")).isEqualTo(AgreementAppliedVisaStatus.DENIED);
    }

    @Test
    public void should_convert_from_enum_to_code() {
        assertThat(converter.convertToDatabaseColumn(AgreementAppliedVisaStatus.GRANTED)).isEqualTo("G");
    }
}
