package org.bdickele.sptransp.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.bdickele.sptransp.domain.UserRole.*;

/**
 * Created by bdickele
 */
public enum UserProfile {

    CUSTOMER("CUSTOMER", "Customer", false, ROLE_CUSTOMER),

    ADMIN_ALL("ADMIN_ALL", "Admin all rights", true,
            ROLE_EMPLOYEE_LIST_READER, ROLE_EMPLOYEE_WRITER, ROLE_RULE_READER,
            ROLE_RULE_WRITER, ROLE_AGREEMENT_VISA_APPLIER, ROLE_REQUEST_READER),

    ADMIN_READER("ADMIN_READER", "Admin reader", true,
            ROLE_EMPLOYEE_LIST_READER, ROLE_RULE_READER, ROLE_REQUEST_READER),

    AGREEMENT_VISA_APPLIER("AGR_VISA_APPLIER", "Agreement visa applier", true,
            ROLE_RULE_READER, ROLE_AGREEMENT_VISA_APPLIER, ROLE_REQUEST_READER),

    RULE_WRITER("RULE_WRITER", "Rule writer", true,
            ROLE_RULE_READER, ROLE_RULE_WRITER, ROLE_REQUEST_READER),

    HR_READER("HR_READER", "Human resources - Reader", true,
            ROLE_EMPLOYEE_LIST_READER),

    HR_WRITER("HR_WRITER", "Human resources - Writer", true,
            ROLE_EMPLOYEE_LIST_READER, ROLE_EMPLOYEE_WRITER);


    private final String code;

    private final String label;

    private final boolean employeeProfile;

    private List<UserRole> roles;

    private static List<UserProfile> EMPLOYEE_PROFILES = null;


    UserProfile(String code, String label, boolean employeeProfile, UserRole... roles) {
        this.code = code;
        this.label = label;
        this.employeeProfile = employeeProfile;
        this.roles = Arrays.asList(roles);
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public boolean isEmployeeProfile() {
        return employeeProfile;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public static UserProfile getByCode(String code) {
        for (UserProfile profile : UserProfile.values()) {
            if (profile.code.equals(code)) return profile;
        }

        return null;
    }

    public static List<UserProfile> getEmployeeProfiles() {
        if (EMPLOYEE_PROFILES==null) {
            EMPLOYEE_PROFILES = Arrays.asList(UserProfile.values()).stream()
                    .filter(UserProfile::isEmployeeProfile)
                    .collect(Collectors.toList());
        }
        return EMPLOYEE_PROFILES;
    }
}
