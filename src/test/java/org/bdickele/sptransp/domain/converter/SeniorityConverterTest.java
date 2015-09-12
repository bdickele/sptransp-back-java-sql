package org.bdickele.sptransp.domain.converter;

import org.bdickele.sptransp.domain.Seniority;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class SeniorityConverterTest {

    private final SeniorityConverter converter = new SeniorityConverter();

    @Test
    public void should_convert_from_integer_to_seniority() {
        Seniority seniority = converter.convertToEntityAttribute(20);
        assertThat(seniority.getValue()).isEqualTo(20);
    }

    @Test
    public void should_convert_from_seniority_to_integer() {
        Integer value = converter.convertToDatabaseColumn(new Seniority(50));
        assertThat(value).isEqualTo(50);
    }
}
