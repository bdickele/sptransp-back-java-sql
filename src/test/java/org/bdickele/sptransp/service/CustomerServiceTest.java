package org.bdickele.sptransp.service;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.bdickele.sptransp.domain.Customer;
import org.bdickele.sptransp.domain.Profile;
import org.bdickele.sptransp.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static com.ninja_squad.dbsetup.Operations.sql;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class CustomerServiceTest extends AbstractServiceTest {

    public static final Operation TEST_CUSTOMER_DELETE = sequenceOf(
            sql("delete from ST_CUSTOMER where full_name = 'CUST1_NAME' "),
            sql("delete from ST_USER where uid_user = 'CUST1' "));

    // The tracker is static because JUnit uses a separate Test instance for every test method.
    private static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private DataSource dataSource;


    @Before
    public void setUp() {
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), sequenceOf(
                TEST_CUSTOMER_DELETE));
        // Use the tracker to launch the DbSetup.
        dbSetupTracker.launchIfNecessary(dbSetup);
    }

    @Test
    public void insertion_should_work() {
        Customer customer = repository.findByUid("CUST1");
        assertThat(customer).isNull();

        // The test writes to the database, so dbSetupTracker.skipNextLaunch(); must NOT be called
        Long id = service.create("CUST1", "CUST1_NAME", "testuser");
        assertThat(id).isNotNull();

        customer = repository.findByUid("CUST1");
        assertThat(customer.getId()).isEqualTo(id);
        assertThat(customer.getProfile()).isEqualTo(Profile.CUSTOMER);
    }
}
