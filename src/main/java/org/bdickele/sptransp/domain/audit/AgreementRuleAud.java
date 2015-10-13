package org.bdickele.sptransp.domain.audit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bdickele.sptransp.domain.AgreementRule;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_AGREEMENT_RULE_AUD")
@EqualsAndHashCode(of = "pk", doNotUseGetters = true)
@ToString(of = {"pk", "destinationId", "goodsId"}, doNotUseGetters = true)
@Getter
public class AgreementRuleAud implements Serializable {

    private static final long serialVersionUID = -6181403943144041262L;

    @EmbeddedId
    private AgreementRuleAudPK pk;

    @Column(name = "ID_DESTINATION")
    private Long destinationId;

    @Column(name = "ID_GOODS")
    private Long goodsId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name="ID_RULE", referencedColumnName="ID_RULE"),
        @JoinColumn(name="RULE_VERSION", referencedColumnName="VERSION")
    })
    @OrderBy("RANK ASC")
    private List<AgreementRuleVisaAud> visas;

    @Column(name = "VERSION_DATE")
    private LocalDateTime versionDate;

    @Column(name = "VERSION_USER")
    private String versionUser;


    /**
     * Builds a AgreementRuleAud object based on a AgreementRuleAud.
     * We increment version by one as version of AgreementRule is incremented by Hibernate on commit
     * @param rule
     * @return
     */
    public static AgreementRuleAud build(AgreementRule rule) {
        AgreementRuleAud audit = new AgreementRuleAud();
        audit.pk = AgreementRuleAudPK.build(rule.getId(), rule.getVersion() + 1);
        audit.destinationId = rule.getDestination().getId();
        audit.goodsId = rule.getGoods().getId();
        audit.versionDate = rule.getUpdateDate();
        audit.versionUser = rule.getUpdateUser();

        audit.visas = rule.getVisas().stream()
                .map(v -> AgreementRuleVisaAud.build(rule, v))
                .collect(Collectors.toList());

        return audit;
    }
}
