package org.bdickele.sptransp.controller;

import org.bdickele.sptransp.controller.dto.GoodsDTO;
import org.bdickele.sptransp.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsRepository repository;


    @RequestMapping(method= RequestMethod.GET,
            produces="application/json")
    public List<GoodsDTO> goods() {
        return GoodsDTO.build(repository.findAllByOrderByNameAsc());
    }
}
