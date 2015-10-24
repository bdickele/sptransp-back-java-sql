package org.bdickele.sptransp.service;

import org.apache.commons.lang3.tuple.Pair;
import org.bdickele.sptransp.domain.*;
import org.bdickele.sptransp.domain.audit.AgreementRuleAud;
import org.bdickele.sptransp.repository.AgreementRuleRepository;
import org.bdickele.sptransp.repository.DestinationRepository;
import org.bdickele.sptransp.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AgreementRuleService extends AbstractService {

    @Autowired
    private AgreementRuleRepository repository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private GoodsRepository goodsRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    public AgreementRule create(String destinationCode, String goodsCode, boolean allowed,
                                List<Pair<Department, Seniority>> departmentAndSeniorities, String creationUser) {
        Destination destination = destinationRepository.findByCode(destinationCode);
        Goods goods = goodsRepository.findByCode(goodsCode);

        AgreementRule rule = AgreementRule.build(null, destination, goods, allowed, creationUser);
        departmentAndSeniorities.forEach(pair -> rule.addVisa(null, pair.getLeft(), pair.getRight()));

        em().persist(rule);

        // Creation of the audit version after calling persist so that we have an ID
        AgreementRuleAud ruleAud = AgreementRuleAud.build(rule, rule.getVersion());
        em().persist(ruleAud);

        return rule;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public AgreementRule update(String destinationCode, String goodsCode, boolean allowed,
                                List<Pair<Department, Seniority>> departmentAndSeniorities, String updateUser) {
        // We assume destination and goods can't change, what means we can retrieve current rule based on these values
        AgreementRule rule = repository.findByDestinationCodeAndGoodsCode(destinationCode, goodsCode);
        rule.update(allowed, departmentAndSeniorities, updateUser);

        // Creation of the audit version after calling persist so that we have an ID
        AgreementRuleAud ruleAud = AgreementRuleAud.build(rule, rule.getVersion()+1);
        em().persist(ruleAud);

        return rule;
    }
}
