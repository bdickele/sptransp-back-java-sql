package org.bdickele.sptransp.domain;

/**
 * Created by Bertrand DICKELE
 */
public final class DomainTestData {

    public static final Good GOOD_OIL = Good.build(1L, "OIL", "Oil", "");
    public static final Good GOOD_FOOD = Good.build(2L, "FOO", "Food", "");
    public static final Good GOOD_MACHINE = Good.build(3L, "MAT", "Machine tool", "");
    public static final Good GOOD_WEAPON = Good.build(4L, "WEA", "Weapon", "");
    public static final Good GOOD_MEDICINE = Good.build(5L, "MED", "Medicine", "");

    public static final Destination DESTINATION_MOON = Destination.build(1L, "MOO", "Moon", "");
    public static final Destination DESTINATION_MARS = Destination.build(2L, "MAR", "Mars", "");
    public static final Destination DESTINATION_TITAN = Destination.build(3L, "TIT", "Titan", "");

    public static final Department DEPARTMENT_LAW_COMPLIANCE = Department.buidl(1L, "Law compliance", "");
    public static final Department DEPARTMENT_SHUTTLE_COMPLIANCE = Department.buidl(2L, "Shuttle compliance", "");
    public static final Department DEPARTMENT_GOOD_INSPECTION = Department.buidl(3L, "Good inspection", "");
    public static final Department DEPARTMENT_JOURNEY_SUPERVISOR = Department.buidl(4L, "Journey supervisor", "");
}
