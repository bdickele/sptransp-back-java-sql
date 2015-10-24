package org.bdickele.sptransp.domain;

/**
 * Status related to agreement visas
 * Created by bdickele
 */
public enum RequestAgreementStatus {

    CANCELLED("C", "Cancelled"),

    PENDING("P", "Pending"),

    GRANTED("G", "Granted"),

    REFUSED("R", "Refused");

    public final String databaseCode;

    public final String label;


    RequestAgreementStatus(String code, String label) {
        this.databaseCode = code;
        this.label = label;
    }

    public static RequestAgreementStatus getByCode(String code) {
        for (RequestAgreementStatus status : RequestAgreementStatus.values()) {
            if (status.databaseCode.equals(code)) return status;
        }

        return null;
    }
}
