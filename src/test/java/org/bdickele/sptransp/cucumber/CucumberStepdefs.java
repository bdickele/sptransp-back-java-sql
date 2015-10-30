package org.bdickele.sptransp.cucumber;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java8.En;
import org.bdickele.sptransp.domain.*;
import org.bdickele.sptransp.domain.audit.AgreementRuleAud;
import org.junit.Assert;

import java.util.List;

import static org.bdickele.sptransp.domain.DomainTestData.*;

/**
 * Created by Bertrand DICKELE
 */
public class CucumberStepdefs  implements En {

    private static CucumberStepdefs lastInstance;

    private static Long ID = 1L;

    private AgreementRule rule;

    private AgreementRuleAud ruleAud;

    private Request request;

    private Destination destination;

    private Goods goods;


    public CucumberStepdefs() {

        Before((Scenario scenario) -> {
            Assert.assertNotSame(this, lastInstance);
            lastInstance = this;
        });

        Given("^a rule to send food on earth$", () -> {
            destination = DESTINATION_EARTH;
            goods = GOODS_FOOD;
            rule = AgreementRule.build(ID++, destination, goods, true, "test");
        });

        And("^required visas are:$", (DataTable visaTable) -> {
            List<Visa> visas = visaTable.asList(Visa.class);
            visas.forEach(v-> rule.addVisa(ID++, getDepartment(v.department), Seniority.of(v.seniority)));
            ruleAud = AgreementRuleAud.build(rule, rule.getVersion());
        });

        And("^a new request is created for that goods and destination$", () -> {
            request = Request.build(ID++, "ref", CUSTOMER_FOO, goods, DESTINATION_TITAN, destination, ruleAud);
        });

        When("^employee '([\\S\\s]+)' (\\d+) grants his visa$", (String departmentCode, Integer seniority) -> {
            Employee employee = buildEmployee(departmentCode, seniority);
            request.applyAgreementVisa(employee, RequestAgreementVisaStatus.GRANTED, "comment");
        });

        When("^employee '([\\S\\s]+)' (\\d+) denies his visa$", (String departmentCode, Integer seniority) -> {
            Employee employee = buildEmployee(departmentCode, seniority);
            request.applyAgreementVisa(employee, RequestAgreementVisaStatus.DENIED, "comment");
        });

    }

    public static class Visa {
        String department;
        Integer seniority;
    }

    private static Employee buildEmployee(String departmentCode, Integer seniority) {
        String uid = departmentCode + seniority;
        return Employee.build(ID++, uid, uid, UserProfile.AGREEMENT_VISA_APPLIER,
                getDepartment(departmentCode), Seniority.of(seniority), "test", PASSWORD_ENCODER);
    }

    private static Department getDepartment(String departmentCode) {
        switch (departmentCode) {
            case "law compliance": return DEPARTMENT_LAW_COMPLIANCE;
            case "shuttle compliance": return DEPARTMENT_SHUTTLE_COMPLIANCE;
            case "goods inspection": return DEPARTMENT_GOODS_INSPECTION;
            case "journey supervision": return DEPARTMENT_JOURNEY_SUPERVISION;
            default: return null;
        }
    }
}
