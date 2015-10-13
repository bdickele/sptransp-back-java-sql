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
@EqualsAndHashCode(of = {"id", "version"}, doNotUseGetters = true)
@ToString(of = {"id", "version"}, doNotUseGetters = true)
@Getter
public class AgreementRuleAudPK implements Serializable {

    private static final long serialVersionUID = 7541457513897132559L;

    @Column(name = "ID_RULE")
    private Long id;

    @Column(name = "VERSION")
    private Integer version;


    public static AgreementRuleAudPK build(Long id, Integer version) {
        AgreementRuleAudPK pk = new AgreementRuleAudPK();
        pk.id = id;
        pk.version = version;
        return pk;
    }
}
