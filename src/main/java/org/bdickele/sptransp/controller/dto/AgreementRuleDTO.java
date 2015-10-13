package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bdickele.sptransp.domain.AgreementRule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"destinationCode", "goodsCode", "allowed", "agreementsVisas"})
@EqualsAndHashCode(of = {"destinationCode", "goodsCode"}, doNotUseGetters = true)
@ToString(of = {"destinationCode", "goodsCode", "allowed", "agreementVisas"})
@Getter
public class AgreementRuleDTO implements Serializable {

    @JsonProperty(value = "destinationCode")
    private String destinationCode;

    @JsonProperty(value = "goodsCode")
    private String goodsCode;

    @JsonProperty(value = "allowed")
    private boolean allowed;

    @JsonProperty(value = "agreementVisas")
    private List<AgreementRuleVisaDTO> agreementVisas;

    public static AgreementRuleDTO build(AgreementRule rule) {
        AgreementRuleDTO dto = new AgreementRuleDTO();
        dto.destinationCode = rule.getDestination().getCode();
        dto.goodsCode = rule.getGoods().getCode();
        dto.allowed = rule.getAllowed();
        dto.agreementVisas = new ArrayList<>();
        if (dto.allowed && rule.getVisas()!= null) {
            dto.agreementVisas = rule.getVisas().stream().map(v -> AgreementRuleVisaDTO.build(v)).collect(Collectors.toList());
        }
        return dto;
    }
}
