package org.bdickele.sptransp.controller;

import org.bdickele.sptransp.controller.dto.AgreementRuleDTO;
import org.bdickele.sptransp.domain.AgreementRule;
import org.bdickele.sptransp.repository.AgreementRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@RestController
@RequestMapping("/agreementRules")
public class AgreementRulesController extends AbstractController {

    @Autowired
    private AgreementRuleRepository repository;


    @RequestMapping(method= RequestMethod.GET,
            produces="application/json")
    public List<AgreementRuleDTO> rules() {
        List<AgreementRule> rules = repository.findAll();
        return AgreementRuleDTO.build(rules);
    }
}
