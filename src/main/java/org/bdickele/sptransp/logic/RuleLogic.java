package org.bdickele.sptransp.logic;

import org.bdickele.sptransp.domain.AgreementRule;

import java.util.List;
import java.util.Optional;

/**
 * Created by Bertrand DICKELE
 */
public final class RuleLogic {

    /**
     * @param availableRules
     * @param destinationId
     * @param goodId
     * @return Among the list of passed rules, the one with same destination and good
     */
    public static Optional<AgreementRule> findRuleForDestinationAndGood(
            List<AgreementRule> availableRules, Long destinationId, Long goodId) {
        return availableRules.stream()
                .filter(r -> r.getDestinationId().equals(destinationId)
                        && r.getGoodsId().equals(goodId))
                .findFirst();
    }
}
