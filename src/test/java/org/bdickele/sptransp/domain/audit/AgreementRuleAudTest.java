package org.bdickele.sptransp.domain.audit;

import org.bdickele.sptransp.domain.AgreementRule;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bdickele.sptransp.domain.DomainTestData.*;

/**
 * Created by Bertrand DICKELE
 */
public class AgreementRuleAudTest {

    @Test
    public void build_method_should_work() {
        AgreementRule rule = buildRule(1L, DESTINATION_MOON, GOODS_FOOD)
                .addVisa(10L, DEPARTMENT_LAW_COMPLIANCE, SENIORITY_10)
                .addVisa(11L, DEPARTMENT_GOODS_INSPECTION, SENIORITY_50)
                .addVisa(12L, DEPARTMENT_SHUTTLE_COMPLIANCE, SENIORITY_80)
                .addVisa(13L, DEPARTMENT_JOURNEY_SUPERVISION, SENIORITY_50);

        AgreementRuleAud ruleAud = AgreementRuleAud.build(rule);

        assertThat(ruleAud.getPk()).isEqualTo(AgreementRuleAudPK.build(1L, 1));
        assertThat(ruleAud.getDestinationId()).isEqualTo(DESTINATION_MOON.getId());
        assertThat(ruleAud.getGoodsId()).isEqualTo(GOODS_FOOD.getId());
        assertThat(ruleAud.getVersionDate()).isEqualTo(rule.getUpdateDate());

        List<AgreementRuleVisaAud> visas = ruleAud.getVisas();

        assertThat(visas).hasSize(4);

        AgreementRuleVisaAud visa1 = visas.get(0);
        AgreementRuleVisaAud visa2 = visas.get(1);
        AgreementRuleVisaAud visa3 = visas.get(2);
        AgreementRuleVisaAud visa4 = visas.get(3);

        assertThat(visa1.getPk()).isEqualTo(AgreementRuleVisaAudPK.build(1L, 1, 0));
        assertThat(visa1.getDepartment()).isEqualTo(DEPARTMENT_LAW_COMPLIANCE);
        assertThat(visa1.getSeniority()).isEqualTo(SENIORITY_10);

        assertThat(visa2.getPk()).isEqualTo(AgreementRuleVisaAudPK.build(1L, 1, 1));
        assertThat(visa2.getDepartment()).isEqualTo(DEPARTMENT_GOODS_INSPECTION);
        assertThat(visa2.getSeniority()).isEqualTo(SENIORITY_50);

        assertThat(visa3.getPk()).isEqualTo(AgreementRuleVisaAudPK.build(1L, 1, 2));
        assertThat(visa3.getDepartment()).isEqualTo(DEPARTMENT_SHUTTLE_COMPLIANCE);
        assertThat(visa3.getSeniority()).isEqualTo(SENIORITY_80);

        assertThat(visa4.getPk()).isEqualTo(AgreementRuleVisaAudPK.build(1L, 1, 3));
        assertThat(visa4.getDepartment()).isEqualTo(DEPARTMENT_JOURNEY_SUPERVISION);
        assertThat(visa4.getSeniority()).isEqualTo(SENIORITY_50);
    }
}
