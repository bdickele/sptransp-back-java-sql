package org.bdickele.sptransp.domain.converter;

import org.bdickele.sptransp.domain.Profile;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Bertrand DICKELE
 */
@Converter
public class ProfileConverter implements AttributeConverter<Profile, String> {

    @Override
    public String convertToDatabaseColumn(Profile profile) {
        return profile.getCode();
    }

    @Override
    public Profile convertToEntityAttribute(String s) {
        return Profile.getByCode(s);
    }
}
