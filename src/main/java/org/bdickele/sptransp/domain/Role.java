package org.bdickele.sptransp.domain;

/**
 * Created by Bertrand DICKELE
 */
public enum Role {

    ROLE_CUSTOMER("ROLE_CUSTOMER"),

    ROLE_EMPLOYEE_LIST_READER("ROLE_EMPLOYEE_LIST_READER"),

    ROLE_EMPLOYEE_WRITER("ROLE_EMPLOYEE_WRITER"),

    ROLE_RULE_READER("ROLE_RULE_READER"),

    ROLE_RULE_WRITER("ROLE_RULE_WRITER"),

    ROLE_AGREEMENT_VISA_APPLIER("ROLE_AGR_VISA_APPLIER"),

    ROLE_REQUEST_READER("ROLE_REQUEST_READER");


    private final String code;


    Role(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
