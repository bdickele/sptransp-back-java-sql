package org.bdickele.sptransp.domain.audit;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.AgreementRule;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
public class AgreementRuleAud implements Serializable {

    private static final long serialVersionUID = -6181403943144041262L;

    @EmbeddedId
    private AgreementRuleAudPK pk;

    @Column(name = "DELETED")
    private boolean deleted;

    @Column(name = "ID_DESTINATION")
    private Long destination;

    @Column(name = "ID_GOOD")
    private Long good;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name="ID_RULE", referencedColumnName="ID_RULE"),
        @JoinColumn(name="VERSION", referencedColumnName="RULE_VERSION")
    })
    @OrderBy("RANK ASC")
    private List<AgreementRuleVisaAud> visas;

    @Column(name = "VERSION_DATE")
    private LocalDateTime versionDate;

    @Column(name = "VERSION_USER")
    private String versionUser;


    public static AgreementRuleAud build(AgreementRule rule) {
        AgreementRuleAud audit = new AgreementRuleAud();
        audit.pk = AgreementRuleAudPK.build(rule.getId(), rule.getVersion() + 1);
        audit.deleted = rule.isDeleted();
        audit.destination = rule.getDestination().getId();
        audit.good = rule.getGood().getId();
        audit.versionDate = rule.getUpdateDate();
        audit.versionUser = rule.getUpdateUser();

        audit.visas = rule.getVisas().stream()
                .map(v -> AgreementRuleVisaAud.build(rule, v))
                .collect(Collectors.toList());

        return audit;
    }

    public AgreementRuleAudPK getPk() {
        return pk;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Long getDestination() {
        return destination;
    }

    public Long getGood() {
        return good;
    }

    public List<AgreementRuleVisaAud> getVisas() {
        return visas;
    }

    public LocalDateTime getVersionDate() {
        return versionDate;
    }

    public String getVersionUser() {
        return versionUser;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        AgreementRuleAud other = (AgreementRuleAud) obj;
        return new EqualsBuilder()
                .append(this.pk, other.pk)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 11)
                .append(pk)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id/version", pk.getId() + "/" + pk.getVersion())
                .append("delete", deleted)
                .append("destination", destination)
                .append("good", good)
                .toString();
    }
}