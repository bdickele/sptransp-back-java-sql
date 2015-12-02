package org.bdickele.sptransp.domain;

import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Bertrand DICKELE
 * Je vais utiliser JUnitParams pour creer autant de test que de classe
 */
@RunWith(JUnitParamsRunner.class)
public class DomainPlumbingTest {

    private Validator openPojoValidator;


    @Before
    public void setUp() {
        openPojoValidator = ValidatorBuilder.create()
                .with(new SetterTester(), new GetterTester())
                .build();
    }

    @Test
    @Parameters(source = DomainTestClassProvider.class)
    public void test_entities_equals(PojoClass pojoClass) {
        EqualsVerifier.forClass(pojoClass.getClazz()).withRedefinedSuperclass().suppress(Warning.STRICT_INHERITANCE).verify();
    }

    @Test
    @Parameters(source = DomainTestClassProvider.class)
    public void test_entities_getters_setters(PojoClass pojoClass) {
        openPojoValidator.validate(pojoClass);
    }
}
