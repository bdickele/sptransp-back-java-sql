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

    public final String code;

    public final String label;


    RequestAgreementStatus(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static RequestAgreementStatus getByCode(String code) {
        for (RequestAgreementStatus status : RequestAgreementStatus.values()) {
            if (status.code.equals(code)) return status;
        }

        return null;
    }
}
