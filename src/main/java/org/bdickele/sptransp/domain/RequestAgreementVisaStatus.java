package org.bdickele.sptransp.domain;

/**
 * Status of a visa that has been applied, that is it is only Granted or Denied
 * Created by bdickele
 */
public enum RequestAgreementVisaStatus {

    GRANTED("G"),

    DENIED("D");

    public final String databaseCode;

    private RequestAgreementVisaStatus(String s) {
        this.databaseCode = s;
    }

    public static RequestAgreementVisaStatus getByCode(String code) {
        for (RequestAgreementVisaStatus status : RequestAgreementVisaStatus.values()) {
            if (status.databaseCode.equals(code)) return status;
        }

        return null;
    }
}
