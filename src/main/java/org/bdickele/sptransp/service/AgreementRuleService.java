package org.bdickele.sptransp.service;

import org.apache.commons.lang3.tuple.Pair;
import org.bdickele.sptransp.domain.AgreementRule;
import org.bdickele.sptransp.domain.Department;
import org.bdickele.sptransp.domain.Seniority;
import org.bdickele.sptransp.repository.AgreementRuleRepository;
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


    @Transactional(propagation = Propagation.REQUIRED)
    public AgreementRule update(String destinationCode, String goodsCode, boolean allowed,
                                List<Pair<Department, Seniority>> departmentAndSeniorities, String updateUser) {
        // We assume destination and goods can't change, what means we can retrieve current rule based on these values
        AgreementRule rule = repository.findByDestinationCodeAndGoodsCode(destinationCode, goodsCode);
        rule.update(allowed, departmentAndSeniorities, updateUser);
        return rule;
    }
}
