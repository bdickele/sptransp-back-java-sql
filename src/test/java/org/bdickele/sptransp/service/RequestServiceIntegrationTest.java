package org.bdickele.sptransp.service;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import org.bdickele.sptransp.domain.Request;
import org.bdickele.sptransp.domain.RequestAgreementStatus;
import org.bdickele.sptransp.domain.RequestAgreementVisa;
import org.bdickele.sptransp.domain.RequestAgreementVisaStatus;
import org.bdickele.sptransp.repository.RequestRepository;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.sql;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bertrand DICKELE
 */
public class RequestServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Autowired
    private RequestService service;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private DataSource dataSource;

    private Long requestId, visaId;


    @After
    public void after() {
        if (visaId!=null) {
            new DbSetup(new DataSourceDestination(dataSource), sql("delete from ST_REQUEST_AGR_VISA where ID = " + visaId)).launch();
        }

        if (requestId!=null) {
            new DbSetup(new DataSourceDestination(dataSource), sql("delete from ST_REQUEST where ID = " + requestId)).launch();
        }
    }

    @Test
    public void creation_and_update_of_a_request_should_work() {
        // Current number of pending requests
        List<Request> customerRequests = requestRepository.findByCustomerUidAndAgreementStatusInOrderByCreationDate("timulf70", RequestAgreementStatus.PENDING);
        int numberBefore = customerRequests.size();

        // ==== INSERTION ====

        Request request = service.create("FOOD", "MOON", "EARTH", "timulf70");
        requestId = request.getId();
        String reference = request.getReference();
        assertThat(reference).isNotNull();
        assertThat(requestId).isNotNull();

        customerRequests = requestRepository.findByCustomerUidAndAgreementStatusInOrderByCreationDate("timulf70", RequestAgreementStatus.PENDING);
        int numberAfter = customerRequests.size();

        assertThat(numberAfter).isEqualTo(numberBefore+1);

        request = requestRepository.findOne(requestId);
        assertThat(request).isNotNull();
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.PENDING);
        assertThat(request.getAgreementVisas().size()).isEqualTo(0);

        // ==== UPDATE ====

        // We apply the first visa required by someone who is from "Law compliance" department with a seniority >= 20
        // whlofu42 is Helen Cox's UID (Law compliance / 60)
        service.update(reference, "whlofu42", RequestAgreementVisaStatus.GRANTED, "ok");

        request = requestRepository.findByReference(reference);
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.PENDING);
        assertThat(request.getAgreementVisas().size()).isEqualTo(1);

        RequestAgreementVisa visa = request.getAgreementVisas().get(0);
        visaId = visa.getId();
        assertThat(visa.getStatus()).isEqualTo(RequestAgreementVisaStatus.GRANTED);
    }
}
