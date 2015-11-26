package org.bdickele.sptransp.controller;

import com.openpojo.reflection.filters.FilterNonConcrete;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.bdickele.sptransp.controller.dto.*;
import org.junit.Test;

/**
 * Created by Bertrand DICKELE
 */
public class DtoPlumbingTest {

    @Test
    public void test_using_equals_verifier() {
        EqualsVerifier.forClass(AgreementRuleDTO.class)
                .suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
        EqualsVerifier.forClass(AgreementRuleVisaDTO.class)
                .suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
        EqualsVerifier.forClass(CustomerDTO.class)
                .withRedefinedSuperclass().suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
        EqualsVerifier.forClass(DepartmentDTO.class)
                .suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
        EqualsVerifier.forClass(DestinationDTO.class)
                .suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
        EqualsVerifier.forClass(EmployeeDTO.class)
                .withRedefinedSuperclass().suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
        EqualsVerifier.forClass(GoodsDTO.class)
                .suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
        EqualsVerifier.forClass(RequestDTO.class)
                .suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
        EqualsVerifier.forClass(RequestAgreementVisaDTO.class)
                .suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
        EqualsVerifier.forClass(UserDTO.class)
                .suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();
        EqualsVerifier.forClass(UserProfileDTO.class)
                .suppress(Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS).verify();

    }

    @Test
    public void test_dto_getters_setters() {
        Validator validator = ValidatorBuilder.create()
                .with(new SetterTester(), new GetterTester())
                .build();
        validator.validate("org.bdickele.sptransp.controller.dto", new FilterNonConcrete());
    }
}
