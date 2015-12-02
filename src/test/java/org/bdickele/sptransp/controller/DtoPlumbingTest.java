package org.bdickele.sptransp.controller;

import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Bertrand DICKELE
 */
@RunWith(JUnitParamsRunner.class)
public class DtoPlumbingTest {

    @Test
    @Parameters(source = DtoTestClassProvider.class)
    public void test_using_equals_verifier(PojoClass pojoClass) {
        EqualsVerifier.forClass(pojoClass.getClazz())
                .withRedefinedSuperclass()
                .suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    @Parameters(source = DtoTestClassProvider.class)
    public void test_dto_getters_setters(PojoClass pojoClass) {
        Validator validator = ValidatorBuilder.create()
                .with(new SetterTester(), new GetterTester())
                .build();
        validator.validate(pojoClass);
    }
}
