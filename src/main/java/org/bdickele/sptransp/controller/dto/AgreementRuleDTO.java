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
@JsonPropertyOrder({"destinationCode", "destinationName", "goodsCode", "goodsName", "reqAllowed",
    "creationDate", "creationUser", "updateDate", "updateUser", "agreementVisas"})
@EqualsAndHashCode(of = {"destinationCode", "goodsCode"}, doNotUseGetters = true)
@ToString(of = {"destinationCode", "goodsCode", "reqAllowed", "agreementVisas"})
@Getter
public class AgreementRuleDTO implements SpaceTranspDTO, Serializable {

    private static final long serialVersionUID = -4265473341596792743L;

    @JsonProperty(value = "destinationCode")
    private String destinationCode;

    @JsonProperty(value = "destinationName")
    private String destinationName;

    @JsonProperty(value = "goodsCode")
    private String goodsCode;

    @JsonProperty(value = "goodsName")
    private String goodsName;

    @JsonProperty(value = "reqAllowed")
    private boolean reqAllowed;

    @JsonProperty(value = "agreementVisas")
    private List<AgreementRuleVisaDTO> agreementVisas;

    @JsonProperty(value = "creationDate")
    protected String creationDate;

    @JsonProperty(value = "creationUser")
    protected String creationUser;

    @JsonProperty(value = "updateDate")
    protected String updateDate;

    @JsonProperty(value = "updateUser")
    protected String updateUser;


    public static List<AgreementRuleDTO> build(List<AgreementRule> rules) {
        return rules.stream().map(AgreementRuleDTO::build).collect(Collectors.toList());
    }

    public static AgreementRuleDTO build(AgreementRule rule) {
        AgreementRuleDTO dto = new AgreementRuleDTO();
        dto.destinationCode = rule.getDestination().getCode();
        dto.destinationName = rule.getDestination().getName();
        dto.goodsCode = rule.getGoods().getCode();
        dto.goodsName = rule.getGoods().getName();
        dto.reqAllowed = rule.getAllowed();
        dto.creationDate = dto.formatDate(rule.getCreationDate());
        dto.creationUser = rule.getCreationUser();
        dto.updateDate = dto.formatDate(rule.getUpdateDate());
        dto.updateUser = rule.getUpdateUser();
        dto.agreementVisas = new ArrayList<>();
        if (rule.getVisas()!= null) {
            dto.agreementVisas = rule.getVisas().stream().map(v -> AgreementRuleVisaDTO.build(v)).collect(Collectors.toList());
        }
        return dto;
    }
}
