package org.bdickele.sptransp.controller.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
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
@EqualsAndHashCode(of = "reference", doNotUseGetters = true)
@ToString(of = {"reference", "customerUid", "departureCode", "arrivalCode", "goodsCode", "creationDate"}, doNotUseGetters = true)
@Getter
public class RequestDTO implements SpaceTranspDTO, Serializable {

    private static final long serialVersionUID = -3314219597285942969L;

    private String reference;

    private String creationDate;

    private Long creationDateForComparison;

    private String updateDate;

    private Long updateDateForComparison;

    private String customerUid;

    private String customerName;

    private String goodsCode;

    private String goodsName;

    private String departureCode;

    private String departureName;

    private String arrivalCode;

    private String arrivalName;

    private String overallStatusCode;

    private String overallStatusLabel;

    private String agreementStatusCode;

    private String agreementStatusLabel;

    private AgreementRuleVisaDTO nextExpectedAgreementVisa;

    // Detailed informations

    private Integer nextAgreementVisaRank;

    private List<RequestAgreementVisaDTO> appliedAgreementVisas;

    private List<AgreementRuleVisaDTO> requiredAgreementVisas;


    public static RequestDTO build(Request request, boolean writeDetails) {
        RequestDTO dto = new RequestDTO();
        dto.reference = request.getReference();
        dto.creationDate = dto.formatDate(request.getCreationDate());
        dto.creationDateForComparison = Long.valueOf(dto.formatDateForComparison(request.getCreationDate()));
        dto.updateDate = dto.formatDate(request.getUpdateDate());
        dto.updateDateForComparison = Long.valueOf(dto.formatDateForComparison(request.getUpdateDate()));
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
        dto.nextAgreementVisaRank = request.getNextAgreementVisaRank();

        Optional<AgreementRuleVisaAud> nextExpectedVisa = request.getNextExpectedAgreementVisa();
        dto.nextExpectedAgreementVisa = nextExpectedVisa.isPresent() ?
                AgreementRuleVisaDTO.build(nextExpectedVisa.get()) : null;

        if (writeDetails) {
            dto.appliedAgreementVisas = request.getAgreementVisas()==null ? new ArrayList<>() :
                    request.getAgreementVisas().stream()
                            .map(RequestAgreementVisaDTO::build)
                            .collect(Collectors.toList());

            dto.requiredAgreementVisas = request.getRuleAud().getVisas().stream()
                    .map(AgreementRuleVisaDTO::build)
                    .collect(Collectors.toList());
        }

        return dto;
    }

    public static List<RequestDTO> build(List<Request> requests, boolean writeDetails) {
        return requests.stream()
                .map(r -> RequestDTO.build(r, writeDetails))
                .collect(Collectors.toList());
    }
}
