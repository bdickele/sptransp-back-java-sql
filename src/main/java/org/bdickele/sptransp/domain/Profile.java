package org.bdickele.sptransp.domain;

import java.util.Arrays;
import java.util.List;

import static org.bdickele.sptransp.domain.Role.*;

/**
 * Created by bdickele
 */
public enum Profile {

    CUSTOMER("CUSTOMER", ROLE_CUSTOMER),

    ADMIN_ALL("ADMIN_ALL",
            ROLE_EMPLOYEE_LIST_READER, ROLE_EMPLOYEE_WRITER, ROLE_RULE_READER,
            ROLE_RULE_WRITER, ROLE_AGREEMENT_VISA_APPLIER, ROLE_REQUEST_READER),

    ADMIN_READER("ADMIN_READER",
            ROLE_EMPLOYEE_LIST_READER, ROLE_RULE_READER, ROLE_REQUEST_READER),

    AGREEMENT_VISA_APPLIER("AGR_VISA_APPLIER",
            ROLE_RULE_READER, ROLE_AGREEMENT_VISA_APPLIER, ROLE_REQUEST_READER),

    RULE_WRITER("RULE_WRITER",
            ROLE_RULE_READER, ROLE_RULE_WRITER, ROLE_REQUEST_READER),

    HR_READER("HR_READER",
            ROLE_EMPLOYEE_LIST_READER),

    HR_WRITER("HR_WRITER",
            ROLE_EMPLOYEE_LIST_READER, ROLE_EMPLOYEE_WRITER);



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
