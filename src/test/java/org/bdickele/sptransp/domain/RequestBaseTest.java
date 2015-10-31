package org.bdickele.sptransp.domain;

import org.bdickele.sptransp.domain.audit.AgreementRuleAud;
import org.bdickele.sptransp.exception.SpTranspError;
import org.bdickele.sptransp.exception.SpTranspException;
import org.bdickele.sptransp.exception.SpTranspTechError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bdickele.sptransp.domain.DomainTestData.*;
import static org.bdickele.sptransp.exception.SpTranspBizError.*;

/**
 * We check here exceptions are raised when mandatory data are missing
 * We also check equals, hashCode and toString
 * Created by Bertrand DICKELE
 */
public class RequestBaseTest {

    private static final AgreementRule RULE = buildRule(1L, DESTINATION_MOON, GOODS_FOOD)
            .addVisa(10L, DEPARTMENT_LAW_COMPLIANCE, SENIORITY_10)
            .addVisa(11L, DEPARTMENT_GOODS_INSPECTION, SENIORITY_50)
            .addVisa(12L, DEPARTMENT_SHUTTLE_COMPLIANCE, SENIORITY_80)
            .addVisa(13L, DEPARTMENT_JOURNEY_SUPERVISION, SENIORITY_50);

    private String reference;
    private Customer customer;
    private Goods goods;
    private Destination departure;
    private Destination arrival;
    private AgreementRuleAud ruleVersion;

    private Request request;


    @Before
    public void before() {
        reference = "reference";
        customer = CUSTOMER_FOO;
        goods = GOODS_FOOD;
        departure = DESTINATION_EARTH;
        arrival = DESTINATION_MOON;
        ruleVersion = AgreementRuleAud.build(RULE, 1);
    }

    @Test
    public void hashCode_and_equals_are_based_on_id() {
        // r1 and r2 don't have the same destinations and arrival but have same ID
        // rr1 and r2 have the same base values but not the same ID
        Request r1 = Request.build(1L, reference, customer, goods, departure, arrival, ruleVersion);
        Request r2 = Request.build(1L, reference, customer, goods, arrival, departure, ruleVersion);
        Request r3 = Request.build(2L, reference, customer, goods, departure, arrival, ruleVersion);

        assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
        assertThat(r1.hashCode()).isNotEqualTo(r3.hashCode());

        assertThat(r1).isEqualTo(r2);
        assertThat(r1).isNotEqualTo(r3);
    }

    @Test
    public void toString_should_work() {
        buildRequest();
        assertThat(request.toString()).isEqualTo("Request(id=null, reference=reference, " +
                        "customer=Customer[id=13,uid=CUSTOMER_UID,fullName=The Customer], " +
                        "goods=Goods(id=2, code=FOOD, name=Food), departure=Destination(id=1, code=EARTH, name=Earth), " +
                        "arrival=Destination(id=2, code=MOON, name=Moon), " +
                        "overallStatus=WAITING_FOR_VALIDATION, agreementStatus=PENDING)");
    }

    @Test
    public void all_mandatory_values_are_set() {
        Request.build(null, reference, customer, goods, departure, arrival, ruleVersion);
    }

    @Test
    public void exception_when_reference_is_missing() {
        test_error_when_creating_a_request(() -> reference = null, REQUEST_MISSING_VALUE);
        test_error_when_creating_a_request(() -> reference = "", REQUEST_MISSING_VALUE);
    }

    @Test
    public void exception_when_customer_is_missing() {
        test_error_when_creating_a_request(() -> customer = null, REQUEST_MISSING_VALUE);
    }

    @Test
    public void exception_when_goods_is_missing() {
        test_error_when_creating_a_request(() -> goods = null, REQUEST_MISSING_VALUE);
    }

    @Test
    public void exception_when_departure_is_missing() {
        test_error_when_creating_a_request(() -> departure = null, REQUEST_MISSING_VALUE);
    }

    @Test
    public void exception_when_arrival_is_missing() {
        test_error_when_creating_a_request(() -> arrival = null, REQUEST_MISSING_VALUE);
    }

    @Test
    public void exception_when_departure_and_arrival_are_the_same() {
        test_error_when_creating_a_request(() -> arrival = departure, DESTINATION_AND_ARRIVAL_ARE_THE_SAME);
    }

    @Test
    public void exception_when_rule_is_missing() {
        test_error_when_creating_a_request(() -> ruleVersion = null, REQUEST_MISSING_VALUE);
    }

    @Test
    public void exception_when_request_is_not_allowed_for_that_goods_and_destination() {
        test_error_when_creating_a_request(() -> ReflectionTestUtils.setField(ruleVersion, "allowed", false),
                REQUEST_NOT_ALLOWED);
    }

    @Test
    public void exception_when_creation_info_are_missing() {
        test_error_when_modifying_a_request(() -> ReflectionTestUtils.setField(request, "creationUser", null),
                SpTranspTechError.MISSING_INFORMATION);
        test_error_when_modifying_a_request(() -> ReflectionTestUtils.setField(request, "creationUser", ""),
                SpTranspTechError.MISSING_INFORMATION);
        test_error_when_modifying_a_request(() -> {
                    ReflectionTestUtils.setField(request, "creationUser", "user");
                    ReflectionTestUtils.setField(request, "creationDate", null);
                },
                SpTranspTechError.MISSING_INFORMATION
        );
    }

    @Test
    public void exception_when_update_info_are_missing() {
        test_error_when_modifying_a_request(() -> ReflectionTestUtils.setField(request, "updateUser", null),
                SpTranspTechError.MISSING_INFORMATION);
        test_error_when_modifying_a_request(() -> ReflectionTestUtils.setField(request, "updateUser", ""),
                SpTranspTechError.MISSING_INFORMATION);
        test_error_when_modifying_a_request(() -> {
                    ReflectionTestUtils.setField(request, "updateUser", "user");
                    ReflectionTestUtils.setField(request, "updateDate", null);
                },
                SpTranspTechError.MISSING_INFORMATION
        );
    }

    private void test_error_when_creating_a_request(Runnable r, SpTranspError expectedError) {
        try {
            r.run();
            buildRequest();
            Assert.fail("Building a request was supposed to throw an error");
        } catch(SpTranspException e) {
            assertThat(e.getError()).isEqualTo(expectedError);
        }
    }

    private void test_error_when_modifying_a_request(Runnable r, SpTranspError expectedError) {
        try {
            buildRequest();
            r.run();
            request.checkValues();
            Assert.fail("Method checkValues() was supposed to throw an error");
        } catch(SpTranspException e) {
            assertThat(e.getError()).isEqualTo(expectedError);
        }
    }

    private void buildRequest() {
        request = Request.build(null, reference, customer, goods, departure, arrival, ruleVersion);
    }

}
