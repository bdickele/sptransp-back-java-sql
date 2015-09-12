package org.bdickele.sptransp.domain;

/**
 * Status of a visa that has been applied, that is it is only Granted or Denied
 * Created by bdickele
 */
public enum AgreementAppliedVisaStatus {

    GRANTED("G"),

    DENIED("D");

    public final String databaseCode;

    private AgreementAppliedVisaStatus(String s) {
        this.databaseCode = s;
    }

    public static AgreementAppliedVisaStatus getByCode(String code) {
        for (AgreementAppliedVisaStatus status : AgreementAppliedVisaStatus.values()) {
            if (status.databaseCode.equals(code)) return status;
        }

        return null;
    }
}
