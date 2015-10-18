package org.bdickele.sptransp.domain;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Bertrand DICKELE
 */
public final class DomainTestData {

    private static Long ID = 1L;

    public static final String USER_UID = "userUid";

    public static final PasswordEncoder PASSWORD_ENCODER = NoOpPasswordEncoder.getInstance();

    public static final Goods GOODS_OIL = Goods.build(ID++, "OIL", "Oil");
    public static final Goods GOODS_FOOD = Goods.build(ID++, "FOOD", "Food");
    public static final Goods GOODS_MACHINE = Goods.build(ID++, "MACHINE_TOOL", "Machine tool");
    public static final Goods GOODS_WEAPON = Goods.build(ID++, "WEAPON", "Weapon");
    public static final Goods GOODS_MEDICINE = Goods.build(ID++, "MEDICINE", "Medicine");

    public static final Destination DESTINATION_EARTH = Destination.build(ID++, "EARTH", "Earth");
    public static final Destination DESTINATION_MOON = Destination.build(ID++, "MOON", "Moon");
    public static final Destination DESTINATION_MARS = Destination.build(ID++, "MARS", "Mars");
    public static final Destination DESTINATION_TITAN = Destination.build(ID++, "TITAN", "Titan");

    public static final Department DEPARTMENT_LAW_COMPLIANCE = Department.buidl(ID++, "LAW_COMPLIANCE", "Law compliance");
    public static final Department DEPARTMENT_SHUTTLE_COMPLIANCE = Department.buidl(ID++, "SHUTTLE_COMPLIANCE", "Shuttle compliance");
    public static final Department DEPARTMENT_GOODS_INSPECTION = Department.buidl(ID++, "GOODS_INSPECTION", "Good inspection");
    public static final Department DEPARTMENT_JOURNEY_SUPERVISION = Department.buidl(ID++, "JOURNEY_SUPERVISION", "Journey supervisor");

    public static final Seniority SENIORITY_10 = Seniority.of(10);
    public static final Seniority SENIORITY_50 = Seniority.of(50);
    public static final Seniority SENIORITY_80 = Seniority.of(80);

    public static final Employee EMP_LAW_10 = buildEmployee(ID++, DEPARTMENT_LAW_COMPLIANCE, SENIORITY_10);
    public static final Employee EMP_LAW_50 = buildEmployee(ID++, DEPARTMENT_LAW_COMPLIANCE, SENIORITY_50);
    public static final Employee EMP_LAW_80 = buildEmployee(ID++, DEPARTMENT_LAW_COMPLIANCE, SENIORITY_80);

    public static final Employee EMP_GOO_10 = buildEmployee(ID++, DEPARTMENT_GOODS_INSPECTION, SENIORITY_10);
    public static final Employee EMP_GOO_50 = buildEmployee(ID++, DEPARTMENT_GOODS_INSPECTION, SENIORITY_50);
    public static final Employee EMP_GOO_80 = buildEmployee(ID++, DEPARTMENT_GOODS_INSPECTION, SENIORITY_80);

    public static final Employee EMP_SHU_10 = buildEmployee(ID++, DEPARTMENT_SHUTTLE_COMPLIANCE, SENIORITY_10);
    public static final Employee EMP_SHU_50 = buildEmployee(ID++, DEPARTMENT_SHUTTLE_COMPLIANCE, SENIORITY_50);
    public static final Employee EMP_SHU_80 = buildEmployee(ID++, DEPARTMENT_SHUTTLE_COMPLIANCE, SENIORITY_80);

    public static final Employee EMP_JOU_10 = buildEmployee(ID++, DEPARTMENT_JOURNEY_SUPERVISION, SENIORITY_10);
    public static final Employee EMP_JOU_50 = buildEmployee(ID++, DEPARTMENT_JOURNEY_SUPERVISION, SENIORITY_50);
    public static final Employee EMP_JOU_80 = buildEmployee(ID++, DEPARTMENT_JOURNEY_SUPERVISION, SENIORITY_80);

    public static final Customer CUSTOMER_FOO = Customer.build(ID++, "CUSTOMER_UID", "The Customer", USER_UID, PASSWORD_ENCODER);


    public static AgreementRule buildRule(Long id, Destination destination, Goods goods) {
        return AgreementRule.build(id, destination, goods, USER_UID);
    }

    public static Employee buildEmployee(Long id, Department department, Seniority seniority) {
        String name = department.getName() + "-" + seniority.getValue();
        return Employee.build(id, "UID_" + name, name, UserProfile.ADMIN_ALL, department, seniority,
                USER_UID, PASSWORD_ENCODER);
    }
}
