package org.bdickele.sptransp.controller;

import org.bdickele.sptransp.controller.dto.DestinationDTO;
import org.bdickele.sptransp.domain.Destination;
import org.bdickele.sptransp.exception.SpTranspBizError;
import org.bdickele.sptransp.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@RestController
@RequestMapping("/destinations")
public class DestinationController extends AbstractController {

    @Autowired
    private DestinationRepository repository;


    @RequestMapping(method= RequestMethod.GET,
            produces="application/json")
    public List<DestinationDTO> destinations() {
        return DestinationDTO.build(repository.findAllByOrderByNameAsc());
    }

    @RequestMapping(value="/{code}", method= RequestMethod.GET,
            produces="application/json")
    public DestinationDTO destination(@PathVariable String code) {
        Destination destination = repository.findByCode(code);

        if (destination==null) {
            throw SpTranspBizError.DESTINATION_NOT_FOUND.exception(code);
        }

        return DestinationDTO.build(destination);
    }
}
