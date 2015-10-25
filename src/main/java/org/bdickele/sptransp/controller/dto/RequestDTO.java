package org.bdickele.sptransp.controller.dto;

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
@EqualsAndHashCode(of = "reference", doNotUseGetters = true)
@ToString(of = {"reference", "customerUid", "departureCode", "arrivalCode", "goodsCode", "creationDate"}, doNotUseGetters = true)
@Getter
public class RequestDTO implements SpaceTranspDTO, Serializable {

    private static final long serialVersionUID = -3314219597285942969L;

    protected String reference;

    protected String creationDate;

    protected String updateDate;

    protected String customerUid;

    protected String customerName;

    protected String goodsCode;

    protected String goodsName;

    protected String departureCode;

    protected String departureName;

    protected String arrivalCode;

    protected String arrivalName;

    protected String overallStatusCode;

    protected String overallStatusLabel;

    protected String agreementStatusCode;

    protected String agreementStatusLabel;

    protected AgreementRuleVisaDTO nextExpectedAgreementVisa;


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
        dto.overallStatusCode = request.getOverallStatus().getCode();
        dto.overallStatusLabel = request.getOverallStatus().getLabel();
        dto.agreementStatusCode = request.getAgreementStatus().getCode();
        dto.agreementStatusLabel = request.getAgreementStatus().getLabel();

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
