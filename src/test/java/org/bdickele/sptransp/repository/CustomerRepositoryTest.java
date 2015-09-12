package org.bdickele.sptransp.repository;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.bdickele.sptransp.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.insertInto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.tuple;

/**
 * Created by Bertrand DICKELE
 */
public class CustomerRepositoryTest extends AbstractRepositoryTest {

    // The tracker is static because JUnit uses a separate Test instance for every test method.
    private static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private DataSource dataSource;

    // Insertion of Customer test data
    public static final Operation CUSTOMER_INSERT = insertInto("ST_CUSTOMER")
            .columns("ID", "UID", "FULL_NAME")
            .values(1, "CUST1", "NAME1")
            .values(2, "CUST2", "NAME2")
            .build();


    @Before
    public void setUp() {
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), CUSTOMER_INSERT);
        // Use the tracker to launch the DbSetup.
        dbSetupTracker.launchIfNecessary(dbSetup);
    }

    @Test
    public void findAll_should_work() {
        dbSetupTracker.skipNextLaunch();

        dbSetupTracker.skipNextLaunch();

        List<Customer> customers = repository.findAll();
        assertThat(customers).extracting("uid", "fullName").containsExactly(
                tuple("CUST1", "NAME1"),
                tuple("CUST2", "NAME2"));
    }

    @Test
    public void find_by_id_should_work() {
        dbSetupTracker.skipNextLaunch();

        Customer customer = repository.findOne(99L);
        assertThat(customer).isNull();

        customer = repository.findOne(1L);
        assertThat(customer).isNotNull();
        assertThat(customer.getUid()).isEqualTo("CUST1");
    }

    @Test
    public void insertion_through_repository_should_work() {
        // The test writes to the database, so dbSetupTracker.skipNextLaunch(); must NOT be called
        Customer customer = Customer.build("CUST3", "NAME3");
        assertThat(customer.getId()).isNull();

        repository.save(customer);

        assertThat(customer.getId()).isNotNull();
    }


}
