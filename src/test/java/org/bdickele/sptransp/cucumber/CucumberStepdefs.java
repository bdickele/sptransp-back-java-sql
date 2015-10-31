package org.bdickele.sptransp.cucumber;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java8.En;
import org.bdickele.sptransp.domain.*;
import org.bdickele.sptransp.domain.audit.AgreementRuleAud;
import org.bdickele.sptransp.domain.audit.AgreementRuleVisaAud;
import org.bdickele.sptransp.exception.SpTranspError;
import org.bdickele.sptransp.exception.SpTranspException;
import org.junit.Assert;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.fail;
import static org.bdickele.sptransp.domain.DomainTestData.*;
import static org.bdickele.sptransp.exception.SpTranspBizError.VISA_TO_APPLY_DOESNT_MATCH_NEXT_EXPECTED_ONE;

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

    private SpTranspError error;


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

        Given("^required visas are:$", (DataTable visaTable) -> {
            List<Visa> visas = visaTable.asList(Visa.class);
            visas.forEach(v -> rule.addVisa(ID++, getDepartment(v.department), Seniority.of(v.seniority)));
            ruleAud = AgreementRuleAud.build(rule, rule.getVersion());
        });

        When("^a new request is created for that goods and destination$", () -> {
            request = Request.build(ID++, "ref", CUSTOMER_FOO, goods, DESTINATION_TITAN, destination, ruleAud);
        });

        When("^employee '([\\S\\s]+)' (\\d+) grants his visa$", (String departmentCode, Integer seniority) -> {
            error = null;
            Employee employee = buildEmployee(departmentCode, seniority);
            try {
                request.applyAgreementVisa(employee, RequestAgreementVisaStatus.GRANTED, "comment");
            } catch (SpTranspException e) {
                error = e.getError();
            }
        });

        When("^employee '([\\S\\s]+)' (\\d+) denies his visa$", (String departmentCode, Integer seniority) -> {
            error = null;
            Employee employee = buildEmployee(departmentCode, seniority);
            try {
                request.applyAgreementVisa(employee, RequestAgreementVisaStatus.DENIED, "comment");
            } catch (SpTranspException e) {
                error = e.getError();
            }
        });

        Then("^agreement status of request is (\\S+)$", (String statusLabel) -> {
            RequestAgreementStatus status = RequestAgreementStatus.getByLabel(statusLabel);
            assertThat(request.getAgreementStatus()).isEqualTo(status);
        });

        Then("^rank of next expected agreement visa is ([-\\d]+)$", (Integer rank) -> {
            assertThat(request.getNextAgreementVisaRank()).isEqualTo(rank);
        });

        Then("^next expected agreement visa is '([\\S\\s]+)' (\\d+)$", (String departmentCode, Integer seniorityValue) -> {
            Optional<AgreementRuleVisaAud> optionNextExpectedVisa = request.getNextExpectedAgreementVisa();
            if (!optionNextExpectedVisa.isPresent()) {
                fail("We expected an agreement visa");
            } else {
                // Expected values
                Department department = getDepartment(departmentCode);
                Seniority seniority = Seniority.of(seniorityValue);
                // Actual visa
                AgreementRuleVisaAud visa = optionNextExpectedVisa.get();

                assertThat(visa.getDepartment().getCode()).isEqualTo(department.getCode());
                assertThat(visa.getSeniority()).isEqualTo(seniority);
            }
        });

        Then("^we don't expect any agreement visa$", () -> {
            Optional<AgreementRuleVisaAud> optionNextExpectedVisa = request.getNextExpectedAgreementVisa();
            if (optionNextExpectedVisa.isPresent()) {
                fail("We don't expect an agreement visa anymore");
            }
        });

        Then("^employee should not be allowed$", () -> {
            assertThat(error).isNotNull();
            assertThat(error).isEqualTo(VISA_TO_APPLY_DOESNT_MATCH_NEXT_EXPECTED_ONE);
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
