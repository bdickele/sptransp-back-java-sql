package org.bdickele.sptransp.controller.dto;

import lombok.Getter;
import org.bdickele.sptransp.domain.Request;
import org.bdickele.sptransp.domain.audit.AgreementRuleVisaAud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
@Getter
public class RequestDetailsDTO extends RequestDTO implements SpaceTranspDTO, Serializable {

    private static final long serialVersionUID = -8472656644963494464L;

    private List<RequestAgreementVisaDTO> appliedAgreementVisas;


    public static RequestDetailsDTO build(Request request) {
        RequestDetailsDTO dto = new RequestDetailsDTO();
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

        dto.appliedAgreementVisas = request.getAgreementVisas()==null ? new ArrayList<>() :
                request.getAgreementVisas().stream()
                        .map(RequestAgreementVisaDTO::build)
                        .collect(Collectors.toList());

        return dto;
    }
}
