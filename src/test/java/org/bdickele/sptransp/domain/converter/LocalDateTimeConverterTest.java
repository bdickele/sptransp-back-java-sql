package org.bdickele.sptransp.domain.converter;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class LocalDateTimeConverterTest {

    private LocalDateTimeConverter converter = new LocalDateTimeConverter();


    @Test
    public void should_convert_from_localdatetime_to_timestamp() {
        LocalDateTime time = LocalDateTime.of(2015, 10, 25, 21, 45, 2, 700_000);
        Timestamp timestamp = converter.convertToDatabaseColumn(time);
        assertThat(timestamp.toLocalDateTime()).isEqualTo(time);
    }

    @Test
    public void should_convert_from_null_localdatetime_to_null() {
        Timestamp timestamp = converter.convertToDatabaseColumn(null);
        assertThat(timestamp).isNull();
    }

    @Test
    public void should_convert_from_timestamp_to_localdatetime() {
        Timestamp timestamp = new Timestamp(300);
        LocalDateTime time = converter.convertToEntityAttribute(timestamp);
        assertThat(timestamp.toLocalDateTime()).isEqualTo(time);
    }

    @Test
    public void should_convert_from_null_timestamp_to_null() {
        LocalDateTime time = converter.convertToEntityAttribute(null);
        assertThat(time).isNull();
    }
}
