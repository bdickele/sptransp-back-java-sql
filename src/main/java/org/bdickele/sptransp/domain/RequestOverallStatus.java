package org.bdickele.sptransp.domain;

/**
 * Created by bdickele
 */
public enum RequestOverallStatus {

    // Initial state of request: agreement visas are requested
    PENDING("P"),

    // Cancelled
    CANCELLED("C"),

    // Someone denied his visa
    REFUSED("R"),

    // All agreement visas have been granted
    VALIDATED("V"),

    // In transit
    IN_TRANSIT("T"),

    // Goods delivered
    DELIVERED("D");


    public final String databaseCode;

    private RequestOverallStatus(String s) {
        this.databaseCode = s;
    }

    public static RequestOverallStatus getByCode(String code) {
        for (RequestOverallStatus status : RequestOverallStatus.values()) {
            if (status.databaseCode.equals(code)) return status;
        }

        return null;
    }
}
