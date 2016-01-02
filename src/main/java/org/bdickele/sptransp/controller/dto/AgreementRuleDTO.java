package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bdickele.sptransp.domain.AgreementRule;
import org.bdickele.sptransp.domain.audit.AgreementRuleAud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"destinationCode", "destinationName", "goodsCode", "goodsName", "allowed",
    "creationDate", "creationUser", "updateDate", "updateUser", "visas"})
@EqualsAndHashCode(of = {"destinationCode", "goodsCode"}, doNotUseGetters = true)
@ToString(of = {"destinationCode", "goodsCode", "allowed", "visas"})
@Getter
public class AgreementRuleDTO implements SpaceTranspDTO, Serializable {

    private static final long serialVersionUID = -4265473341596792743L;

    private String destinationCode;

    private String destinationName;

    private String goodsCode;

    private String goodsName;

    private boolean allowed;

    private List<AgreementRuleVisaDTO> visas;

    protected String creationDate;

    protected String creationUser;

    protected String updateDate;

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
        dto.allowed = rule.getAllowed();
        dto.creationDate = dto.formatDate(rule.getCreationDate());
        dto.creationUser = rule.getCreationUser();
        dto.updateDate = dto.formatDate(rule.getUpdateDate());
        dto.updateUser = rule.getUpdateUser();
        dto.visas = new ArrayList<>();
        if (rule.getVisas()!= null) {
            dto.visas = rule.getVisas().stream().map(v -> AgreementRuleVisaDTO.build(v)).collect(Collectors.toList());
        }
        return dto;
    }

    public static AgreementRuleDTO build(AgreementRuleAud rule) {
        AgreementRuleDTO dto = new AgreementRuleDTO();
        dto.destinationCode = rule.getDestination().getCode();
        dto.destinationName = rule.getDestination().getName();
        dto.goodsCode = rule.getGoods().getCode();
        dto.goodsName = rule.getGoods().getName();
        dto.allowed = rule.getAllowed();
        dto.visas = new ArrayList<>();
        if (rule.getVisas()!= null) {
            dto.visas = rule.getVisas().stream().map(v -> AgreementRuleVisaDTO.build(v)).collect(Collectors.toList());
        }
        return dto;
    }
}
