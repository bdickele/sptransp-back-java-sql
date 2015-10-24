package org.bdickele.sptransp.controller.dto;

import lombok.Getter;
import lombok.ToString;
import org.bdickele.sptransp.domain.RequestAgreementVisa;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
@ToString(of = {"departmentCode", "seniority", "employeeUid"})
@Getter
public class RequestAgreementVisaDTO implements SpaceTranspDTO, Serializable {

    private static final long serialVersionUID = 5984205835227083895L;

    private String employeeUid;

    private String employeeName;

    private String statusCode;

    private String departmentCode;

    private String departmentName;

    private Integer seniority;

    private String comment;

    private String date;


    public static RequestAgreementVisaDTO build(RequestAgreementVisa visa) {
        RequestAgreementVisaDTO dto = new RequestAgreementVisaDTO();
        dto.employeeUid = visa.getEmployee().getUid();
        dto.employeeName = visa.getEmployee().getFullName();
        dto.statusCode = visa.getStatus().code;
        dto.departmentCode = visa.getDepartment().getCode();
        dto.departmentName = visa.getDepartment().getName();
        dto.seniority = visa.getSeniority().getValue();
        dto.comment = visa.getComment();
        dto.date = dto.formatDate(visa.getCreationDate());
        return dto;
    }

    public static List<RequestAgreementVisaDTO> build(List<RequestAgreementVisa> visas) {
        return visas.stream()
                .map(RequestAgreementVisaDTO::build)
                .collect(Collectors.toList());
    }
}
