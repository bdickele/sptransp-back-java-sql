package org.bdickele.sptransp.domain;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;

import java.util.function.Predicate;

/**
 * Created by Bertrand DICKELE
 * Cette classe sera utilisee par JUnitParams pour passer les classes du package domain une par une
 */
public class DomainTestClassProvider {

    private static final String DOMAIN_PACKAGE = "org.bdickele.sptransp.domain";

    private static final String AUDIT_PACKAGE = "org.bdickele.sptransp.domain.audit";

    private static final Predicate<PojoClass> NOT_A_TEST_CLASS =
            pojoClass -> !pojoClass.getClazz().getSimpleName().contains("Test");


    public static Object[] provideDomainClasses() {
        return PojoClassFactory.getPojoClasses(DOMAIN_PACKAGE, null)
                .stream()
                .filter(NOT_A_TEST_CLASS)
                .toArray();
    }

    public static Object[] provideAuditClasses() {
        return PojoClassFactory.getPojoClasses(AUDIT_PACKAGE, null)
                .stream()
                .filter(NOT_A_TEST_CLASS)
                .toArray();
    }
}
