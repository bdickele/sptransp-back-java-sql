package org.bdickele.sptransp.domain.converter;

import org.bdickele.sptransp.domain.AgreementAppliedVisaStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Bertrand DICKELE
 */
@Converter
public class AgreementAppliedVisaStatusConverter implements AttributeConverter<AgreementAppliedVisaStatus, String> {


    @Override
    public String convertToDatabaseColumn(AgreementAppliedVisaStatus status) {
        return status.databaseCode;
    }

    @Override
    public AgreementAppliedVisaStatus convertToEntityAttribute(String code) {
        return AgreementAppliedVisaStatus.getByCode(code);
    }
}
