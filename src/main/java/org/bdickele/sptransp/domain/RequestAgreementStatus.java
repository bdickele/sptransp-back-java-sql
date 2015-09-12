package org.bdickele.sptransp.domain;

/**
 * Status related to agreement visas
 * Created by bdickele
 */
public enum RequestAgreementStatus {

    PENDING("P"),

    GRANTED("G"),

    REFUSED("R");

    public final String databaseCode;

    private RequestAgreementStatus(String s) {
        this.databaseCode = s;
    }

    public static RequestAgreementStatus getByCode(String code) {
        for (RequestAgreementStatus status : RequestAgreementStatus.values()) {
            if (status.databaseCode.equals(code)) return status;
        }

        return null;
    }
}
