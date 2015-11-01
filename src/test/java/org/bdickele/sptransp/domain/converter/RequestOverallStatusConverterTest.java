package org.bdickele.sptransp.domain.converter;

import org.bdickele.sptransp.domain.RequestOverallStatus;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class RequestOverallStatusConverterTest {

    private final RequestOverallStatusConverter converter = new RequestOverallStatusConverter();

    @Test
    public void should_convert_from_code_to_enum() {
        assertThat(converter.convertToEntityAttribute("WV")).isEqualTo(RequestOverallStatus.WAITING_FOR_VALIDATION);
    }

    @Test
    public void should_convert_from_enum_to_code() {
        assertThat(converter.convertToDatabaseColumn(RequestOverallStatus.CANCELLED)).isEqualTo("C");
    }
}
