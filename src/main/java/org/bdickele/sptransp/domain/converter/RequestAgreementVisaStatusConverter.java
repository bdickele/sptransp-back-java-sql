package org.bdickele.sptransp.domain.converter;

import org.bdickele.sptransp.domain.RequestAgreementVisaStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Bertrand DICKELE
 */
@Converter
public class RequestAgreementVisaStatusConverter implements AttributeConverter<RequestAgreementVisaStatus, String> {

    @Override
    public String convertToDatabaseColumn(RequestAgreementVisaStatus visaStatus) {
        return visaStatus.code;
    }

    @Override
    public RequestAgreementVisaStatus convertToEntityAttribute(String s) {
        return RequestAgreementVisaStatus.getByCode(s);
    }
}
