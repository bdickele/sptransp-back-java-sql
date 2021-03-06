package org.bdickele.sptransp.domain.audit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bdickele.sptransp.domain.AgreementRule;
import org.bdickele.sptransp.domain.Destination;
import org.bdickele.sptransp.domain.Goods;
import org.bdickele.sptransp.domain.converter.LocalDateTimeConverter;

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
@ToString(of = {"pk", "destination", "goods"}, doNotUseGetters = true)
@Getter
public class AgreementRuleAud implements Serializable {

    private static final long serialVersionUID = -6181403943144041262L;

    @EmbeddedId
    private AgreementRuleAudPK pk;

    @ManyToOne
    @JoinColumn(name = "ID_DESTINATION")
    private Destination destination;

    @ManyToOne
    @JoinColumn(name = "ID_GOODS")
    private Goods goods;

    @Column(name = "REQ_ALLOWED")
    private Boolean allowed;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "ruleAud")
    @OrderBy("VISA_RANK ASC")
    private List<AgreementRuleVisaAud> visas;

    @Column(name = "VERSION_DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime versionDate;

    @Column(name = "VERSION_USER")
    private String versionUser;


    /**
     * Builds a AgreementRuleAud object based on a AgreementRuleAud.
     * We increment version by one as version of AgreementRule is incremented by Hibernate on commit
     * @param rule
     * @param version
     * @return
     */
    public static AgreementRuleAud build(AgreementRule rule, Integer version) {
        AgreementRuleAud audit = new AgreementRuleAud();
        audit.pk = AgreementRuleAudPK.build(rule.getId(), version);
        audit.destination = rule.getDestination();
        audit.goods = rule.getGoods();
        audit.allowed = rule.getAllowed();
        audit.versionDate = rule.getUpdateDate();
        audit.versionUser = rule.getUpdateUser();

        audit.visas = rule.getVisas().stream()
                .map(v -> AgreementRuleVisaAud.build(audit, v))
                .collect(Collectors.toList());

        return audit;
    }
}
