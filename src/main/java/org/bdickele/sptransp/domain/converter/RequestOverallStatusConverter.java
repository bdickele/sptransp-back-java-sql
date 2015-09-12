package org.bdickele.sptransp.domain.converter;

import org.bdickele.sptransp.domain.RequestOverallStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Bertrand DICKELE
 */
@Converter
public class RequestOverallStatusConverter implements AttributeConverter<RequestOverallStatus, String> {

    @Override
    public String convertToDatabaseColumn(RequestOverallStatus status) {
        return status.databaseCode;
    }

    @Override
    public RequestOverallStatus convertToEntityAttribute(String code) {
        return RequestOverallStatus.getByCode(code);
    }
}
