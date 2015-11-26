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

        // ==== INSERTION ====

        Request request = service.create("FOOD", "MOON", "EARTH", "timulf70");
        requestId = request.getId();
        String reference = request.getReference();
        assertThat(reference).isNotNull();
        assertThat(requestId).isNotNull();

        request = requestRepository.findOne(requestId);
        assertThat(request).isNotNull();
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.PENDING);
        assertThat(request.getAgreementVisas().size()).isEqualTo(0);

        // ==== UPDATE ====

        // We apply the first visa required by someone who is from "Law compliance" department with a seniority >= 20
        service.update(reference, "qlomny06", RequestAgreementVisaStatus.GRANTED, "ok");

        request = requestRepository.findByReference(reference);
        assertThat(request.getAgreementStatus()).isEqualTo(RequestAgreementStatus.PENDING);
        assertThat(request.getAgreementVisas().size()).isEqualTo(1);

        RequestAgreementVisa visa = request.getAgreementVisas().get(0);
        visaId = visa.getId();
        assertThat(visa.getStatus()).isEqualTo(RequestAgreementVisaStatus.GRANTED);
    }
}
