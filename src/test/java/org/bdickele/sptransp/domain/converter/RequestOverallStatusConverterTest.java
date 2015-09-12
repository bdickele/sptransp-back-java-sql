package org.bdickele.sptransp.domain.converter;

import org.bdickele.sptransp.domain.RequestOverallStatus;
import org.junit.Test;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class RequestOverallStatusConverterTest {

    private final RequestOverallStatusConverter converter = new RequestOverallStatusConverter();

    @Test
    public void should_convert_from_code_to_enum() {
        assertThat(converter.convertToEntityAttribute("P")).isEqualTo(RequestOverallStatus.PENDING);
    }

    @Test
    public void should_convert_from_enum_to_code() {
        assertThat(converter.convertToDatabaseColumn(RequestOverallStatus.CANCELLED)).isEqualTo("C");
    }
}
