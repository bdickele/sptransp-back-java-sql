package org.bdickele.sptransp.controller;

import org.bdickele.sptransp.controller.dto.RequestAgreementVisaDTO;
import org.bdickele.sptransp.controller.dto.RequestDTO;
import org.bdickele.sptransp.controller.dto.RequestDetailsDTO;
import org.bdickele.sptransp.domain.Request;
import org.bdickele.sptransp.domain.RequestAgreementStatus;
import org.bdickele.sptransp.domain.RequestAgreementVisaStatus;
import org.bdickele.sptransp.repository.RequestRepository;
import org.bdickele.sptransp.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.bdickele.sptransp.domain.RequestAgreementStatus.*;

/**
 * Created by Bertrand DICKELE
 */
@RestController
@RequestMapping("/requests")
public class RequestController extends AbstractController {

    @Autowired
    private RequestService service;

    @Autowired
    private RequestRepository repository;


    @RequestMapping(
            value="/{requestReference}",
            method= RequestMethod.GET,
            produces="application/json")
    public RequestDetailsDTO request(@PathVariable String requestReference) {
        Request request = repository.findByReference(requestReference);
        return request==null ? null : RequestDetailsDTO.build(request);
    }

    @RequestMapping(
            value="/beingValidated",
            method= RequestMethod.GET,
            produces="application/json")
    public List<RequestDTO> requestsBeingValidated() {
        return getRequestsPerStatus(PENDING);
    }

    @RequestMapping(
            value="/beingValidated/{customerUid}",
            method= RequestMethod.GET,
            produces="application/json")
    public List<RequestDTO> requestsBeingValidated(@PathVariable String customerUid) {
        return getRequestsPerCustomerAndStatus(customerUid, PENDING);
    }

    @RequestMapping(
            value="/grantedOrRefused",
            method= RequestMethod.GET,
            produces="application/json")
    public List<RequestDTO> requestsGrantedOrRefused() {
        return getRequestsPerStatus(GRANTED, REFUSED);
    }

    @RequestMapping(
            value="/grantedOrRefused/{customerUid}",
            method= RequestMethod.GET,
            produces="application/json")
    public List<RequestDTO> requestsGrantedOrRefused(@PathVariable String customerUid) {
        return getRequestsPerCustomerAndStatus(customerUid, GRANTED, REFUSED);
    }

    private List<RequestDTO> getRequestsPerStatus(RequestAgreementStatus... agreementStatus) {
        List<Request> requests  = repository.findByAgreementStatusIn(agreementStatus);
        return RequestDTO.build(requests);
    }

    private List<RequestDTO> getRequestsPerCustomerAndStatus(String customerUid, RequestAgreementStatus... agreementStatus) {
        List<Request> requests  = repository.findByCustomerUidAndAgreementStatusIn(customerUid, agreementStatus);
        return RequestDTO.build(requests);
    }

    @RequestMapping(
            method= RequestMethod.POST,
            consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public RequestDTO create(@RequestBody RequestDTO dto) {
        Request request = service.create(dto.getGoodsCode(), dto.getDepartureCode(), dto.getArrivalCode(), dto.getCustomerUid());
        return RequestDTO.build(request);
    }

    @RequestMapping(
            value = "/{requestReference}",
            method= RequestMethod.PUT,
            consumes="application/json")
    @ResponseStatus(HttpStatus.OK)
    public RequestDTO applyVisa(@PathVariable String requestReference, @RequestBody RequestAgreementVisaDTO dto) {
        Request request = service.update(requestReference, dto.getEmployeeUid(),
                RequestAgreementVisaStatus.getByCode(dto.getStatusCode()), dto.getComment());
        return RequestDTO.build(request);
    }
}
