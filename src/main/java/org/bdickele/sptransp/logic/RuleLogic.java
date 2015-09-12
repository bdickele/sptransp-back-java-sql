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
                .filter(r -> r.getDestination().getId().equals(destinationId)
                        && r.getGood().getId().equals(goodId))
                .findFirst();
    }
}
