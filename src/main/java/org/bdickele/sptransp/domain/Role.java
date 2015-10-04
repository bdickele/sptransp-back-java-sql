package org.bdickele.sptransp.domain;

/**
 * Created by Bertrand DICKELE
 */
public enum Role {

    EMPLOYEE_LIST_READER("EMPLOYEE_LIST_READER"),

    EMPLOYEE_WRITER("EMPLOYEE_WRITER"),

    RULE_READER("RULE_READER"),

    RULE_WRITER("RULE_WRITER"),

    AGREEMENT_VISA_APPLIER("AGREEMENT_VISA_APPLIER"),

    REQUEST_READER("REQUEST_READER");


    private final String code;


    Role(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
