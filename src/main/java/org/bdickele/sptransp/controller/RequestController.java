package org.bdickele.sptransp.controller;

import org.bdickele.sptransp.controller.dto.RequestDTO;
import org.bdickele.sptransp.domain.Request;
import org.bdickele.sptransp.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@RestController
@RequestMapping("/requests")
public class RequestController extends AbstractController {

    @Autowired
    private RequestService service;


    @RequestMapping(
            value="/beingValidated",
            method= RequestMethod.GET,
            produces="application/json")
    public List<RequestDTO> requestsBeingValidated() {
        List<Request> requests  = service.findRequestsBeingValidated();
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


}
