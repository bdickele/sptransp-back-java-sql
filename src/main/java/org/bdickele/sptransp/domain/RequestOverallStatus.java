package org.bdickele.sptransp.domain;

/**
 * Created by bdickele
 */
public enum RequestOverallStatus {

    // Initial state of request: agreement visas are requested
    WAITING_FOR_VALIDATION("WV", "Waiting for validation"),

    // Cancelled
    CANCELLED("C", "Cancelled"),

    // Someone denied his visa
    REFUSED("R", "Refused"),

    // All agreement visas have been granted
    VALIDATED("V", "Validated"),

    // In transit
    IN_TRANSIT("T", "In transit"),

    // Goods delivered
    DELIVERED("D", "Delivered");


    public final String code;

    public final String label;

    private RequestOverallStatus(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static RequestOverallStatus getByCode(String code) {
        for (RequestOverallStatus status : RequestOverallStatus.values()) {
            if (status.code.equals(code)) return status;
        }

        return null;
    }
}
