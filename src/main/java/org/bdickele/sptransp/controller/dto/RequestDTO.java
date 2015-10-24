package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bdickele.sptransp.domain.Request;
import org.bdickele.sptransp.domain.audit.AgreementRuleVisaAud;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"reference", "creationDate", "updateDate", "customerUid", "customerName", "goodsCode", "goodsName",
        "departureCode", "departureName", "arrivalCode", "arrivalName", "agreementStatusCode", "agreementStatusName",
        "nextExpectedAgreementVisa"})
@EqualsAndHashCode(of = "reference", doNotUseGetters = true)
@ToString(of = {"reference", "customerUid", "departureCode", "arrivalCode", "goodsCode", "creationDate"}, doNotUseGetters = true)
@Getter
public class RequestDTO implements SpaceTranspDTO, Serializable {

    private static final long serialVersionUID = -3314219597285942969L;

    private String reference;

    private String creationDate;

    private String updateDate;

    private String customerUid;

    private String customerName;

    private String goodsCode;

    private String goodsName;

    private String departureCode;

    private String departureName;

    private String arrivalCode;

    private String arrivalName;

    private String agreementStatusCode;

    private String agreementStatusName;

    private AgreementRuleVisaDTO nextExpectedAgreementVisa;


    public static RequestDTO build(Request request) {
        RequestDTO dto = new RequestDTO();
        dto.reference = request.getReference();
        dto.creationDate = dto.formatDate(request.getCreationDate());
        dto.updateDate = dto.formatDate(request.getUpdateDate());
        dto.customerUid = request.getCustomer().getUid();
        dto.customerName = request.getCustomer().getFullName();
        dto.goodsCode = request.getGoods().getCode();
        dto.goodsName = request.getGoods().getName();
        dto.departureCode = request.getDeparture().getCode();
        dto.departureName = request.getDeparture().getName();
        dto.arrivalCode = request.getArrival().getCode();
        dto.arrivalName = request.getArrival().getName();
        dto.agreementStatusCode = request.getAgreementStatus().databaseCode;
        dto.agreementStatusName = request.getAgreementStatus().label;

        Optional<AgreementRuleVisaAud> nextExpectedVisa = request.getNextExpectedAgreementVisa();
        dto.nextExpectedAgreementVisa = nextExpectedVisa.isPresent() ?
                AgreementRuleVisaDTO.build(nextExpectedVisa.get()) : null;

        return dto;
    }

    public static List<RequestDTO> build(List<Request> requests) {
        return requests.stream()
                .map(RequestDTO::build)
                .collect(Collectors.toList());
    }
}
