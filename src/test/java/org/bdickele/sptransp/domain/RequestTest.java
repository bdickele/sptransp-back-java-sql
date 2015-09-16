package org.bdickele.sptransp.domain;

import org.bdickele.sptransp.domain.audit.AgreementRuleAud;
import org.bdickele.sptransp.exception.SpTranspBizError;
import org.bdickele.sptransp.exception.SpTranspException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.function.Consumer;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.assertj.core.api.StrictAssertions.fail;
import static org.bdickele.sptransp.domain.DomainTestData.*;
import static org.bdickele.sptransp.exception.SpTranspBizError.*;

/**
 * Created by Bertrand DICKELE
 */
public class RequestTest {

    private static final AgreementRule RULE = buildRule(1L, DESTINATION_MOON, GOODS_FOOD)
            .addVisa(10L, DEPARTMENT_LAW_COMPLIANCE, SENIORITY_10)
            .addVisa(11L, DEPARTMENT_GOODS_INSPECTION, SENIORITY_50)
            .addVisa(12L, DEPARTMENT_SHUTTLE_COMPLIANCE, SENIORITY_80)
            .addVisa(13L, DEPARTMENT_JOURNEY_SUPERVISOR, SENIORITY_50);

    private static final AgreementRuleAud RULE_AUD = AgreementRuleAud.build(RULE);

    private static final LocalDateTime NOW = LocalDateTime.now();

    private Request request;


    @Before
    public void setUp() {
        request = Request.build(100L, "UID_REQUEST", CUSTOMER_FOO, GOODS_FOOD, DESTINATION_MOON, RULE_AUD);
    }

    @Test
    public void request_should_be_validated() {
        request.applyAgreementVisa(EMP_LAW_10, RequestAgreementVisaStatus.GRANTED, "C", NOW);
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.PENDING);

        request.applyAgreementVisa(EMP_GOO_50, RequestAgreementVisaStatus.GRANTED, "C", NOW);
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.PENDING);

        request.applyAgreementVisa(EMP_SHU_80, RequestAgreementVisaStatus.GRANTED, "C", NOW);
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.PENDING);

        request.applyAgreementVisa(EMP_JOU_50, RequestAgreementVisaStatus.GRANTED, "C", NOW);
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.GRANTED);
    }

    @Test
    public void request_should_be_refused() {
        request.applyAgreementVisa(EMP_LAW_10, RequestAgreementVisaStatus.GRANTED, "C", NOW);
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.PENDING);

        request.applyAgreementVisa(EMP_GOO_50, RequestAgreementVisaStatus.GRANTED, "C", NOW);
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.PENDING);

        request.applyAgreementVisa(EMP_SHU_80, RequestAgreementVisaStatus.GRANTED, "C", NOW);
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.PENDING);

        request.applyAgreementVisa(EMP_JOU_50, RequestAgreementVisaStatus.DENIED, "C", NOW);
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.REFUSED);
    }

    @Test
    public void applying_visa_should_fail_because_request_is_validated() {
        test_expected_exception(REQUEST_DOES_NOT_EXPECT_ANY_AGREEMENT_VISA,
                req -> req.applyAgreementVisa(EMP_LAW_10, RequestAgreementVisaStatus.DENIED, "C", NOW)
                        .applyAgreementVisa(EMP_GOO_50, RequestAgreementVisaStatus.GRANTED, "C", NOW)
                        .applyAgreementVisa(EMP_SHU_80, RequestAgreementVisaStatus.GRANTED, "C", NOW)
                        .applyAgreementVisa(EMP_JOU_50, RequestAgreementVisaStatus.GRANTED, "C", NOW)
                        .applyAgreementVisa(EMP_JOU_50, RequestAgreementVisaStatus.GRANTED, "C", NOW));
    }

    @Test
    public void applying_visa_should_fail_because_request_is_refused() {
        test_expected_exception(REQUEST_DOES_NOT_EXPECT_ANY_AGREEMENT_VISA,
                req -> req.applyAgreementVisa(EMP_LAW_10, RequestAgreementVisaStatus.DENIED, "C", NOW)
                        .applyAgreementVisa(EMP_GOO_50, RequestAgreementVisaStatus.GRANTED, "C", NOW));
    }

    @Test
    public void applying_visa_should_fail_because_same_user_has_already_applied_a_visa() {
        // I create a user with same ID as the first one to apply a visa
        Employee emp_good_50 = buildEmployee(999L, DEPARTMENT_GOODS_INSPECTION, SENIORITY_50);
        ReflectionTestUtils.setField(emp_good_50, "id", EMP_LAW_10.getId());

        test_expected_exception(EMPLOYEE_HAS_ALREADY_APPLIED_A_VISA,
                req -> req.applyAgreementVisa(EMP_LAW_10, RequestAgreementVisaStatus.GRANTED, "C", NOW)
                        .applyAgreementVisa(EMP_LAW_10, RequestAgreementVisaStatus.GRANTED, "C", NOW));
    }

    @Test
    public void applying_visa_should_fail_because_next_expected_visa_is_not_found() {
        // I override the request with a request without any expected agreement visa
        AgreementRule emptyRule = buildRule(1L, DESTINATION_MOON, GOODS_FOOD);
        AgreementRuleAud emptyRuleAud = AgreementRuleAud.build(emptyRule);
        request = Request.build(101L, "UID_REQUEST", CUSTOMER_FOO, GOODS_FOOD, DESTINATION_MOON, emptyRuleAud);

        test_expected_exception(COULD_NOT_FIND_NEXT_EXPECTED_AGREEMENT_VISA,
                req -> req.applyAgreementVisa(EMP_LAW_10, RequestAgreementVisaStatus.GRANTED, "C", NOW));
    }

    @Test
    public void applying_visa_should_fail_because_user_is_not_from_the_good_department() {
        test_expected_exception(VISA_TO_APPLY_DOESNT_MATCH_NEXT_EXPECTED_ONE,
                req -> req.applyAgreementVisa(EMP_JOU_50, RequestAgreementVisaStatus.GRANTED, "C", NOW));
    }

    @Test
    public void applying_visa_should_fail_because_user_seniority_is_not_sufficient() {
        test_expected_exception(VISA_TO_APPLY_DOESNT_MATCH_NEXT_EXPECTED_ONE,
                req -> req.applyAgreementVisa(EMP_LAW_10, RequestAgreementVisaStatus.GRANTED, "C", NOW)
                            .applyAgreementVisa(EMP_GOO_10, RequestAgreementVisaStatus.GRANTED, "C", NOW));
    }

    private void test_expected_exception(SpTranspBizError expectedError, Consumer<Request> whatToDo) {
        try {
            whatToDo.accept(request);
            fail("Test was not supposed to be successful: exception " + expectedError + " is expected to be thrown");
        } catch (SpTranspException e) {
            assertThat(e.getError()).isEqualTo(expectedError);
        }
    }
}
