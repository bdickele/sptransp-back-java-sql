package org.bdickele.sptransp.domain.audit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Bertrand DICKELE
 */
@Embeddable
@EqualsAndHashCode(of = {"idRule", "version", "rank"}, doNotUseGetters = true)
@ToString(of = {"idRule", "version", "rank"}, doNotUseGetters = true)
@Getter
public class AgreementRuleVisaAudPK implements Serializable {

    private static final long serialVersionUID = -8995397281778177804L;

    @Column(name = "ID_RULE")
    private Long idRule;

    @Column(name = "RULE_VERSION")
    private Integer version;

    @Column(name = "RANK")
    private Integer rank;


    public static AgreementRuleVisaAudPK build(Long idRule, Integer version, Integer rank) {
        AgreementRuleVisaAudPK pk = new AgreementRuleVisaAudPK();
        pk.idRule = idRule;
        pk.version = version;
        pk.rank = rank;
        return pk;
    }
}
