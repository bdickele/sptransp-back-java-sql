package org.bdickele.sptransp.domain.converter;

import org.bdickele.sptransp.domain.RequestAgreementVisaStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Bertrand DICKELE
 */
@Converter
public class AppliedAgreementVisaStatusConverter implements AttributeConverter<RequestAgreementVisaStatus, String> {


    @Override
    public String convertToDatabaseColumn(RequestAgreementVisaStatus status) {
        return status==null ? null : status.getCode();
    }

    @Override
    public RequestAgreementVisaStatus convertToEntityAttribute(String code) {
        return RequestAgreementVisaStatus.getByCode(code);
    }
}
