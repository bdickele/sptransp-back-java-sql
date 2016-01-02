package org.bdickele.sptransp.testdata;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.apache.commons.lang3.tuple.Pair;
import org.bdickele.sptransp.configuration.IntegrationTestConfig;
import org.bdickele.sptransp.domain.*;
import org.bdickele.sptransp.domain.audit.AgreementRuleVisaAud;
import org.bdickele.sptransp.repository.*;
import org.bdickele.sptransp.service.RequestService;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;

/**
 * Created by Bertrand DICKELE
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTestConfig.class)
@Ignore
public class CreateTestDataRequests {

    private static final int NB_REQUESTS = 50;

    public static final Operation DELETE_REQUESTS = deleteAllFrom("ST_REQUEST_AGR_VISA", "ST_REQUEST");

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private RequestService requestService;

    @Autowired
    private DataSource dataSource;

    private CustomerGenerator customerGenerator;

    private EmployeeGenerator employeeGenerator;

    private GoodsGenerator goodsGenerator;

    private DestinationGenerator destinationGenerator;


    @Ignore
    public void createTestData() {
        deleteAllRequests();
        createRequests();
    }

    private void deleteAllRequests() {
        new DbSetup(new DataSourceDestination(dataSource), DELETE_REQUESTS).launch();
    }

    private void createRequests() {
        customerGenerator = new CustomerGenerator(customerRepository.findAll());
        employeeGenerator = new EmployeeGenerator(employeeRepository.findAll());
        goodsGenerator = new GoodsGenerator(goodsRepository.findAll());
        destinationGenerator = new DestinationGenerator(destinationRepository.findAll());

        Random random = new Random();

        for (int i=0; i<NB_REQUESTS; i++) {
            // On cree la requete
            Request request = createRequest();
            //String reference = request.getReference();

            // Je suppose que toutes les regles sont composees selon le schema
            // law compliance > shuttle compliance > goods inspection > journey supervision

            // On va donner entre 0 et 4 visas pour chaque requete
            int nbVisas = random.nextInt(5);
            // Les nbVisas-1 premiers visas seront accordes
            // Pour le dernier visa on tire au sort si il est accorde ou refuse

            if (nbVisas > 0) {
                for (int j=0; j<nbVisas; j++) {
                    AgreementRuleVisaAud nextVisa = request.getNextExpectedAgreementVisa().get();
                    Employee employee = employeeGenerator.get(nextVisa);
                    //System.out.println("Application du visa " + nextVisa.toString() + " par " + employee.toString());
                    RequestAgreementVisaStatus visaStatus = RequestAgreementVisaStatus.GRANTED;

                    // Le dernier visa sera-t-il accorde ou pas ?
                    if (j == (nbVisas-1)) {
                        if (random.nextInt(5)==4) visaStatus = RequestAgreementVisaStatus.DENIED;
                    }

                    // Re-assigner pour avoir un objet mis a jour
                    request = requestService.update(request.getReference(), employee.getUid(), visaStatus, "this is a test data and thus a test comment");
                }
            }
        }
    }

    private Request createRequest() {
        Customer customer = customerGenerator.get();
        Pair<Destination, Destination> destinations = destinationGenerator.get();
        Goods goods = goodsGenerator.get();
        return requestService.create(goods, destinations.getLeft(), destinations.getRight(), customer);
    }

    private static class CustomerGenerator {
        List<Customer> customers;
        int size;
        Random random = new Random();

        private CustomerGenerator(List<Customer> customers) {
            this.customers = customers;
            size = customers.size();
        }

        private Customer get() {
            return customers.get(random.nextInt(size));
        }
    }

    private static class EmployeeGenerator {
        List<Employee> employees;

        private EmployeeGenerator(List<Employee> employees) {
            this.employees = employees;
        }

        private Employee get(AgreementRuleVisaAud visa) {
            return employees.stream()
                    .filter(e -> visa.getDepartment().getCode().equals(e.getDepartment().getCode())
                                    && e.getSeniority().ge(visa.getSeniority()))
                    .findAny()
                    .get();
        }
    }

    private static class GoodsGenerator {
        List<Goods> goodsList;
        int size;
        Random random = new Random();

        private GoodsGenerator(List<Goods> goodsList) {
            this.goodsList = goodsList.stream()
                    .filter(g -> !"WEAPON".equals(g.getCode()))
                    .collect(Collectors.toList());
            this.size = this.goodsList.size();
        }

        private Goods get() {
            return goodsList.get(random.nextInt(size));
        }
    }

    private static class DestinationGenerator {
        List<Destination> destinations;
        int size;
        Random random = new Random();

        private DestinationGenerator(List<Destination> destinations) {
            this.destinations = destinations;
            this.size = destinations.size();
        }

        private Pair<Destination, Destination> get() {
            int firstIndex = random.nextInt(size);
            int secondIndex = random.nextInt(size);
            while (secondIndex==firstIndex) {
                secondIndex = random.nextInt(size);
            }
            return Pair.of(destinations.get(firstIndex), destinations.get(secondIndex));
        }
    }
}
