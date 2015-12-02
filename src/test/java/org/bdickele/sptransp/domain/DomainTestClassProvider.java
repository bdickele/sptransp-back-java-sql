package org.bdickele.sptransp.domain;

import com.openpojo.reflection.impl.PojoClassFactory;

/**
 * Created by Bertrand DICKELE
 * Cette classe sera utilisee par JUnitParams pour passer les classes du package domain une par une
 */
public class DomainTestClassProvider {

    private static final String DOMAIN_PACKAGE = "org.bdickele.sptransp.domain";

    private static final String AUDIT_PACKAGE = "org.bdickele.sptransp.domain.audit";


    public static Object[] provideDomainClasses() {
        return PojoClassFactory.getPojoClasses(DOMAIN_PACKAGE, null)
                .stream()
                .filter(pojoClass -> !pojoClass.getClazz().getSimpleName().contains("Test"))
                .toArray();
    }

    public static Object[] provideAuditClasses() {
        return PojoClassFactory.getPojoClasses(AUDIT_PACKAGE, null)
                .stream()
                .filter(pojoClass -> !pojoClass.getClazz().getSimpleName().contains("Test"))
                .toArray();
    }
}
