package org.bdickele.sptransp.testdata;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.apache.commons.lang3.tuple.Pair;
import org.bdickele.sptransp.configuration.IntegrationTestConfig;
import org.bdickele.sptransp.domain.Department;
import org.bdickele.sptransp.domain.Destination;
import org.bdickele.sptransp.domain.Goods;
import org.bdickele.sptransp.domain.Seniority;
import org.bdickele.sptransp.repository.DestinationRepository;
import org.bdickele.sptransp.repository.GoodsRepository;
import org.bdickele.sptransp.service.AgreementRuleService;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;

/**
 * Created by Bertrand DICKELE
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTestConfig.class)
@Ignore
public class CreateTestDataAgreementRules {

    // Les IDs doivent etre les memes qu'en base sinon ca va donner n'importe quoi
    private static final Department DEPARTMENT_LAW_COMPLIANCE = Department.buidl(1L, "LAW_COMPLIANCE", "Law compliance");
    private static final Department DEPARTMENT_SHUTTLE_COMPLIANCE = Department.buidl(2L, "SHUTTLE_COMPLIANCE", "Shuttle compliance");
    private static final Department DEPARTMENT_GOODS_INSPECTION = Department.buidl(3L, "GOODS_INSPECTION", "Goods inspection");
    private static final Department DEPARTMENT_JOURNEY_SUPERVISION = Department.buidl(4L, "JOURNEY_SUPERVISION", "Journey supervision");

    public static final Operation DELETE_REQUESTS = deleteAllFrom("ST_REQUEST_AGR_VISA", "ST_REQUEST");

    public static final Operation DELETE_AGREEMENT_RULES = deleteAllFrom(
            "ST_AGR_RULE_VISA_AUD", "ST_AGREEMENT_RULE_AUD",
            "ST_AGR_RULE_VISA", "ST_AGREEMENT_RULE");

    private Random random = new Random();

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private AgreementRuleService ruleService;


    @Ignore
    public void createTestData() {
        deleteAllRequests();
        deleteAllAgreementRules();
        createAgreementRules();
    }

    private void deleteAllRequests() {
        new DbSetup(new DataSourceDestination(dataSource), DELETE_REQUESTS).launch();
    }

    private void deleteAllAgreementRules() {
        new DbSetup(new DataSourceDestination(dataSource), DELETE_AGREEMENT_RULES).launch();
    }

    private void createAgreementRules() {
        List<Destination> destinations = destinationRepository.findAll();
        List<Goods> goods = goodsRepository.findAll();

        destinations.forEach(destination -> {
            goods.forEach(good -> createAgreementRule(good, destination));
        });
    }

    private void createAgreementRule(Goods goods, Destination destination) {
        String goodsCode = goods.getCode();
        String destinationCode = destination.getCode();

        List<Pair<Department, Seniority>> visas = new ArrayList<>();
        visas.add(Pair.of(DEPARTMENT_LAW_COMPLIANCE, createGoodsSeniority(goodsCode)));
        visas.add(Pair.of(DEPARTMENT_SHUTTLE_COMPLIANCE, createDistanceSeniority(destinationCode)));
        visas.add(Pair.of(DEPARTMENT_GOODS_INSPECTION, createGoodsSeniority(goodsCode)));
        visas.add(Pair.of(DEPARTMENT_JOURNEY_SUPERVISION, createDistanceSeniority(destinationCode)));

        //print(">> " + goodsCode + " - " + destinationCode);
        //visas.forEach(v -> print(v.getLeft().getCode() + " " + v.getRight().getValue()));

        ruleService.create(destination, goods, true, visas, "batch");
    }

    /**
     * On va creer des seniorites plus elevees pour les destinations lointaines
     * @param destinationCode
     * @return
     */
    private Seniority createDistanceSeniority(String destinationCode) {
        if (destinationCode.startsWith("JUPITER") || destinationCode.startsWith("SATURN")) {
            return seniority(6, 10);
        } else if (destinationCode.startsWith("MARS")) {
            return seniority(3, 7);
        } else {
            return seniority(1, 4);
        }
    }

    private Seniority createGoodsSeniority(String goodsCode) {
        switch (goodsCode) {
            case "OIL" : return seniority(4, 8);
            case "FOOD" : return seniority(1, 4);
            case "MACHINE_TOOL" : return seniority(3, 7);
            case "MEDICINE" : return seniority(4, 9);
            case "WEAPON" : return seniority(8, 10);
            default: return seniority(1, 10);
        }
    }

    private Seniority seniority(int lowerBound, int upperBound) {
        int value = random.ints(lowerBound, upperBound).findFirst().getAsInt();
        return Seniority.of(value * 10);
    }

    private static void print(String s) {
        System.out.println(s);
    }
}
