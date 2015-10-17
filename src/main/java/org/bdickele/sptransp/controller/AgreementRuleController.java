package org.bdickele.sptransp.controller;

import org.bdickele.sptransp.controller.dto.AgreementRuleDTO;
import org.bdickele.sptransp.domain.AgreementRule;
import org.bdickele.sptransp.exception.SpTranspBizError;
import org.bdickele.sptransp.repository.AgreementRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@RestController
@RequestMapping("/agreementRules")
public class AgreementRuleController extends AbstractController {

    @Autowired
    private AgreementRuleRepository repository;


    @RequestMapping(method= RequestMethod.GET,
            produces="application/json")
    public List<AgreementRuleDTO> rules() {
        List<AgreementRule> rules = repository.findAll();
        return AgreementRuleDTO.build(rules);
    }

    @RequestMapping(
            value="/{destinationCode}/{goodsCode}",
            method= RequestMethod.GET,
            produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    public AgreementRuleDTO findByDestinationCodeAndGoodsCode(@PathVariable String destinationCode,
                                                              @PathVariable String goodsCode) {
        AgreementRule rule = repository.findByDestinationCodeAndGoodsCode(destinationCode, goodsCode);

        if (rule==null) {
            throw SpTranspBizError.AGR_RULE_DOESNT_EXIST.exception(destinationCode, goodsCode);
        }

        return AgreementRuleDTO.build(rule);
    }
}
