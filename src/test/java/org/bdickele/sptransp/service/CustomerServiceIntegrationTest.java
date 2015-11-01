package org.bdickele.sptransp.service;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.bdickele.sptransp.domain.Customer;
import org.bdickele.sptransp.domain.UserProfile;
import org.bdickele.sptransp.repository.CustomerRepository;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static com.ninja_squad.dbsetup.Operations.sql;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class CustomerServiceIntegrationTest extends AbstractServiceIntegrationTest {

    public static final Operation TEST_CUSTOMER_DELETE = sequenceOf(
            sql("delete from ST_CUSTOMER where full_name like 'TESTCU_NAME%' "),
            sql("delete from ST_USER where uid_user like 'testcu%' "));

    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private DataSource dataSource;


    @After
    public void after() {
        new DbSetup(new DataSourceDestination(dataSource), TEST_CUSTOMER_DELETE).launch();
    }

    @Test
    public void insertion_and_update_should_work() {
        new DbSetup(new DataSourceDestination(dataSource), TEST_CUSTOMER_DELETE).launch();

        // ==== INSERTION ===

        Customer customer = service.create("TESTCU_NAME", "testuser");
        Long createdId = customer.getId();
        String uid = customer.getUid();

        assertThat(createdId).isNotNull();

        customer = repository.findByUid(uid);
        assertThat(customer.getId()).isEqualTo(createdId);
        assertThat(customer.getProfile()).isEqualTo(UserProfile.CUSTOMER);

        // ==== UPDATE ===

        service.update(uid, "TESTCU_NAME 2", "update user");

        customer = repository.findByUid(uid);
        assertThat(customer.getFullName()).isEqualTo("TESTCU_NAME 2");
        assertThat(customer.getUpdateUser()).isEqualTo("update user");
    }
}
