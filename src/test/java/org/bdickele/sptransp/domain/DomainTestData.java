package org.bdickele.sptransp.domain;

/**
 * Created by Bertrand DICKELE
 */
public final class DomainTestData {

    private static Long ID = 1L;

    public static final String USER_UID = "userUid";

    public static final Goods GOODS_OIL = Goods.build(ID++, "OIL", "Oil", "");
    public static final Goods GOODS_FOOD = Goods.build(ID++, "FOO", "Food", "");
    public static final Goods GOODS_MACHINE = Goods.build(ID++, "MAT", "Machine tool", "");
    public static final Goods GOODS_WEAPON = Goods.build(ID++, "WEA", "Weapon", "");
    public static final Goods GOODS_MEDICINE = Goods.build(ID++, "MED", "Medicine", "");

    public static final Destination DESTINATION_MOON = Destination.build(ID++, "MOO", "Moon", "");
    public static final Destination DESTINATION_MARS = Destination.build(ID++, "MAR", "Mars", "");
    public static final Destination DESTINATION_TITAN = Destination.build(ID++, "TIT", "Titan", "");

    public static final Department DEPARTMENT_LAW_COMPLIANCE = Department.buidl(ID++, "Law compliance", "");
    public static final Department DEPARTMENT_SHUTTLE_COMPLIANCE = Department.buidl(ID++, "Shuttle compliance", "");
    public static final Department DEPARTMENT_GOODS_INSPECTION = Department.buidl(ID++, "Good inspection", "");
    public static final Department DEPARTMENT_JOURNEY_SUPERVISOR = Department.buidl(ID++, "Journey supervisor", "");

    public static final Seniority SENIORITY_10 = new Seniority(10);
    public static final Seniority SENIORITY_50 = new Seniority(50);
    public static final Seniority SENIORITY_80 = new Seniority(80);

    public static final Employee EMP_LAW_10 = buildEmployee(ID++, DEPARTMENT_LAW_COMPLIANCE, SENIORITY_10);
    public static final Employee EMP_LAW_50 = buildEmployee(ID++, DEPARTMENT_LAW_COMPLIANCE, SENIORITY_50);
    public static final Employee EMP_LAW_80 = buildEmployee(ID++, DEPARTMENT_LAW_COMPLIANCE, SENIORITY_80);

    public static final Employee EMP_GOO_10 = buildEmployee(ID++, DEPARTMENT_GOODS_INSPECTION, SENIORITY_10);
    public static final Employee EMP_GOO_50 = buildEmployee(ID++, DEPARTMENT_GOODS_INSPECTION, SENIORITY_50);
    public static final Employee EMP_GOO_80 = buildEmployee(ID++, DEPARTMENT_GOODS_INSPECTION, SENIORITY_80);

    public static final Employee EMP_SHU_10 = buildEmployee(ID++, DEPARTMENT_SHUTTLE_COMPLIANCE, SENIORITY_10);
    public static final Employee EMP_SHU_50 = buildEmployee(ID++, DEPARTMENT_SHUTTLE_COMPLIANCE, SENIORITY_50);
    public static final Employee EMP_SHU_80 = buildEmployee(ID++, DEPARTMENT_SHUTTLE_COMPLIANCE, SENIORITY_80);

    public static final Employee EMP_JOU_10 = buildEmployee(ID++, DEPARTMENT_JOURNEY_SUPERVISOR, SENIORITY_10);
    public static final Employee EMP_JOU_50 = buildEmployee(ID++, DEPARTMENT_JOURNEY_SUPERVISOR, SENIORITY_50);
    public static final Employee EMP_JOU_80 = buildEmployee(ID++, DEPARTMENT_JOURNEY_SUPERVISOR, SENIORITY_80);

    public static final Customer CUSTOMER_FOO = Customer.build(ID++, "CUSTOMER_UID", "The Customer");


    public static AgreementRule buildRule(Long id, Destination destination, Goods goods) {
        return AgreementRule.build(id, destination.getId(), goods.getId(), USER_UID);
    }

    public static Employee buildEmployee(Long id, Department department, Seniority seniority) {
        String name = department.getName() + "-" + seniority.getValue();
        return Employee.build(id, "UID_" + name, name, department, seniority, USER_UID);
    }
}
