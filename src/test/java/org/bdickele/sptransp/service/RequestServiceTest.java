package org.bdickele.sptransp.service;

import org.bdickele.sptransp.domain.Request;
import org.bdickele.sptransp.domain.RequestAgreementVisaStatus;
import org.bdickele.sptransp.exception.SpTranspBizError;
import org.bdickele.sptransp.exception.SpTranspException;
import org.bdickele.sptransp.repository.EmployeeRepository;
import org.bdickele.sptransp.repository.RequestRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Bertrand DICKELE
 */
public class RequestServiceTest {

    @InjectMocks private RequestService service = new RequestService();

    @Mock private RequestRepository requestRepository;

    @Mock private EmployeeRepository employeeRepository;

    @Mock private Request request;


    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_updating_an_exception_is_thrown_when_request_reference_is_incorrect() {
        when(requestRepository.findByReference(anyString())).thenReturn(null);
        try {
            service.update("reference", "employeeUid", RequestAgreementVisaStatus.GRANTED, "comment");
            fail("Test should trow an exception");
        } catch (SpTranspException e) {
            assertThat(e.getError()).isEqualTo(SpTranspBizError.REQUEST_NOT_FOUND_FOR_REFERENCE);
        }
    }

    @Test
    public void when_updating_an_exception_is_thrown_when_employee_uid_is_incorrect() {
        when(requestRepository.findByReference(anyString())).thenReturn(request);
        when(employeeRepository.findByUid(anyString())).thenReturn(null);
        try {
            service.update("reference", "employeeUid", RequestAgreementVisaStatus.GRANTED, "comment");
            fail("Test should trow an exception");
        } catch (SpTranspException e) {
            assertThat(e.getError()).isEqualTo(SpTranspBizError.EMPLOYEE_NOT_FOUND);
        }
    }
}
