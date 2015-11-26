package org.bdickele.sptransp.domain;

import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.bdickele.sptransp.domain.audit.*;
import org.junit.Test;

/**
 * Created by Bertrand DICKELE
 */
public class DomainPlumbingTest {

    @Test
    public void test_entities_equals() {
        EqualsVerifier.forClass(AgreementRule.class).suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(AgreementRuleVisa.class).suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(Customer.class).withRedefinedSuperclass().suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(Department.class).suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(Destination.class).suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(Employee.class).withRedefinedSuperclass().suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(Goods.class).suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(Request.class).suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(RequestAgreementVisa.class).suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(Seniority.class).suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(User.class).suppress(Warning.STRICT_INHERITANCE).verify();
    }

    @Test
    public void test_audit_entities_equals() {
        EqualsVerifier.forClass(AgreementRuleAud.class).suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(AgreementRuleAudPK.class).suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(AgreementRuleVisaAud.class).suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(AgreementRuleVisaAudPK.class).suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(EmployeeAud.class).suppress(Warning.STRICT_INHERITANCE).verify();
        EqualsVerifier.forClass(EmployeeAudPK.class).suppress(Warning.STRICT_INHERITANCE).verify();
    }

    @Test
    public void test_entities_getters_setters() {
        Validator validator = ValidatorBuilder.create()
                .with(new SetterTester(), new GetterTester())
                .build();
        validator.validate("org.bdickele.sptransp.domain");
    }

    @Test
    public void test_audit_entities_getters_setters() {
        Validator validator = ValidatorBuilder.create()
                .with(new SetterTester(), new GetterTester())
                .build();
        validator.validate("org.bdickele.sptransp.domain.audit");
    }
}
