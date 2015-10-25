package org.bdickele.sptransp.domain;

/**
 * Status of a visa that has been applied, that is it is only Granted or Denied
 * Created by bdickele
 */
public enum RequestAgreementVisaStatus {

    GRANTED("G", "Granted"),

    DENIED("D", "Denied");

    private final String code;

    private final String label;


    RequestAgreementVisaStatus(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static RequestAgreementVisaStatus getByCode(String code) {
        for (RequestAgreementVisaStatus status : RequestAgreementVisaStatus.values()) {
            if (status.code.equals(code)) return status;
        }

        return null;
    }
}
