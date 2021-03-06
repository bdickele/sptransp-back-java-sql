package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bdickele.sptransp.domain.AgreementRuleVisa;
import org.bdickele.sptransp.domain.audit.AgreementRuleVisaAud;

import java.io.Serializable;

/**
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"departmentCode", "departmentName", "seniority"})
@EqualsAndHashCode(of = {"departmentCode", "seniority"}, doNotUseGetters = true)
@ToString(of = {"departmentCode", "seniority"})
@Getter
public class AgreementRuleVisaDTO implements Serializable {

    private static final long serialVersionUID = 7918477704866738045L;

    @JsonProperty(value = "departmentCode")
    private String departmentCode;

    @JsonProperty(value = "departmentName")
    private String departmentName;

    @JsonProperty(value = "seniority")
    private Integer seniority;


    public static AgreementRuleVisaDTO build(AgreementRuleVisa visa) {
        AgreementRuleVisaDTO dto = new AgreementRuleVisaDTO();
        dto.departmentCode = visa.getDepartment().getCode();
        dto.departmentName = visa.getDepartment().getName();
        dto.seniority = visa.getSeniority().getValue();
        return dto;
    }

    public static AgreementRuleVisaDTO build(AgreementRuleVisaAud visa) {
        AgreementRuleVisaDTO dto = new AgreementRuleVisaDTO();
        dto.departmentCode = visa.getDepartment().getCode();
        dto.departmentName = visa.getDepartment().getName();
        dto.seniority = visa.getSeniority().getValue();
        return dto;
    }
}
