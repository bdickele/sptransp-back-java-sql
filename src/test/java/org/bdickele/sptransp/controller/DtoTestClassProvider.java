package org.bdickele.sptransp.controller;

import com.openpojo.reflection.filters.FilterNonConcrete;
import com.openpojo.reflection.impl.PojoClassFactory;

/**
 * Created by Bertrand DICKELE
 * Cette classe sera utilisee par JUnitParams pour passer les classes du package dto une par une
 */
public class DtoTestClassProvider {

    private static final String DTO_PACKAGE = "org.bdickele.sptransp.controller.dto";


    public static Object[] provideDtoClasses() {
        return PojoClassFactory.getPojoClasses(DTO_PACKAGE, new FilterNonConcrete())
                .stream()
                .toArray();
    }
}
