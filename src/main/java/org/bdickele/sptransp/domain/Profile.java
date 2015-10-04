package org.bdickele.sptransp.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bdickele
 */
public enum Profile {

    ALL("ALL",
            Role.EMPLOYEE_LIST_READER, Role.EMPLOYEE_WRITER, Role.RULE_READER,
            Role.RULE_WRITER, Role.AGREEMENT_VISA_APPLIER, Role.REQUEST_READER),

    AGREEMENT_VISA_APPLIER("AGREEMENT_VISA_APPLIER",
            Role.RULE_READER, Role.AGREEMENT_VISA_APPLIER, Role.REQUEST_READER),

    RULE_WRITER("RULE_WRITER",
            Role.RULE_READER, Role.RULE_WRITER, Role.REQUEST_READER),

    HR_READER("HR_READER",
            Role.EMPLOYEE_LIST_READER),

    HR_WRITER("HR_WRITER",
            Role.EMPLOYEE_LIST_READER, Role.EMPLOYEE_WRITER),

    READER_ALL("READER_ALL",
            Role.EMPLOYEE_LIST_READER, Role.RULE_READER, Role.REQUEST_READER),;


    private final String code;

    private List<Role> roles;


    Profile(String code, Role... roles) {
        this.code = code;
        this.roles = Arrays.asList(roles);
    }

    public String getCode() {
        return code;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public static Profile getByCode(String code) {
        for (Profile profile : Profile.values()) {
            if (profile.code.equals(code)) return profile;
        }

        return null;
    }
}
