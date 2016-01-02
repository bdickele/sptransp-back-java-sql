package org.bdickele.sptransp.controller;

import org.bdickele.sptransp.controller.dto.RequestAgreementVisaDTO;
import org.bdickele.sptransp.controller.dto.RequestDTO;
import org.bdickele.sptransp.controller.dto.RequestWrapperDTO;
import org.bdickele.sptransp.domain.Request;
import org.bdickele.sptransp.domain.RequestAgreementStatus;
import org.bdickele.sptransp.domain.RequestAgreementVisaStatus;
import org.bdickele.sptransp.exception.SpTranspBizError;
import org.bdickele.sptransp.repository.RequestRepository;
import org.bdickele.sptransp.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;

import static org.bdickele.sptransp.domain.RequestAgreementStatus.*;

/**
 * Created by Bertrand DICKELE
 */
@RestController
@RequestMapping("/requests")
public class RequestController extends AbstractController {

    private static final String DEFAULT_PAGE = "0";

    private static final String DEFAULT_SIZE = "20";

    @Autowired
    private RequestService service;

    @Autowired
    private RequestRepository repository;


    @RequestMapping(
            value="/{requestReference}",
            method= RequestMethod.GET,
            produces="application/json")
    public RequestDTO request(@PathVariable String requestReference) {
        Request request = repository.findByReference(requestReference);

        if (request==null) {
            throw SpTranspBizError.REQUEST_NOT_FOUND.exception(requestReference);
        }

        return RequestDTO.build(request, true);
    }

    @RequestMapping(
            value="/beingValidated",
            method= RequestMethod.GET,
            produces="application/json")
    public RequestWrapperDTO requestsBeingValidated(
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_SIZE) int size) {
        return getRequestsPerStatus(createPageRequest(page, size), PENDING);
    }

    @RequestMapping(
            value="/beingValidated/{customerUid}",
            method= RequestMethod.GET,
            produces="application/json")
    public RequestWrapperDTO requestsBeingValidated(@PathVariable String customerUid,
                                                   @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) int page,
                                                   @RequestParam(value = "size", required = false, defaultValue = DEFAULT_SIZE) int size) {
        return getRequestsPerCustomerAndStatus(createPageRequest(page, size), customerUid, PENDING);
    }

    @RequestMapping(
            value="/grantedOrRefused",
            method= RequestMethod.GET,
            produces="application/json")
    public RequestWrapperDTO requestsGrantedOrRefused(
            @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(value = "size", required = false, defaultValue = DEFAULT_SIZE) int size) {
        return getRequestsPerStatus(createPageRequest(page, size), GRANTED, REFUSED);
    }

    @RequestMapping(
            value="/grantedOrRefused/{customerUid}",
            method= RequestMethod.GET,
            produces="application/json")
    public RequestWrapperDTO requestsGrantedOrRefused(@PathVariable String customerUid,
                                                     @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) int page,
                                                     @RequestParam(value = "size", required = false, defaultValue = DEFAULT_SIZE) int size) {
        return getRequestsPerCustomerAndStatus(createPageRequest(page, size), customerUid, GRANTED, REFUSED);
    }

    private RequestWrapperDTO getRequestsPerStatus(Pageable pageable, RequestAgreementStatus... agreementStatus) {
        Collection<RequestAgreementStatus> statusList = Arrays.asList(agreementStatus);
        Page<Request> requests  = repository.findByAgreementStatusIn(statusList, pageable);
        return new RequestWrapperDTO(pageable, requests);
    }

    private Pageable createPageRequest(int page, int size) {
        return new PageRequest(page, size, Sort.Direction.ASC, "creationDate");
    }

    private RequestWrapperDTO getRequestsPerCustomerAndStatus(Pageable pageable, String customerUid, RequestAgreementStatus... agreementStatus) {
        Collection<RequestAgreementStatus> statusList = Arrays.asList(agreementStatus);
        Page<Request> requests  = repository.findByCustomerUidAndAgreementStatusInOrderByCreationDate(customerUid, statusList, pageable);
        return new RequestWrapperDTO(pageable, requests);
    }

    @RequestMapping(
            method= RequestMethod.POST,
            consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public RequestDTO create(@RequestBody RequestDTO dto) {
        Request request = service.create(dto.getGoodsCode(), dto.getDepartureCode(), dto.getArrivalCode(), dto.getCustomerUid());
        return RequestDTO.build(request, true);
    }

    @RequestMapping(
            value = "/{requestReference}",
            method= RequestMethod.PUT,
            consumes="application/json")
    @ResponseStatus(HttpStatus.OK)
    public RequestDTO applyVisa(@PathVariable String requestReference, @RequestBody RequestAgreementVisaDTO dto) {
        Request request = service.update(requestReference, dto.getEmployeeUid(),
                RequestAgreementVisaStatus.getByCode(dto.getStatusCode()), dto.getComment());
        return RequestDTO.build(request, true);
    }
}
