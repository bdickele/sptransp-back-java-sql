package org.bdickele.sptransp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.bdickele.sptransp.domain.converter.LocalDateTimeConverter;
import org.bdickele.sptransp.exception.SpTranspBizError;
import org.bdickele.sptransp.exception.SpTranspTechError;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_AGREEMENT_RULE")
@SequenceGenerator(name="SEQ_MAIN", sequenceName=DomainConst.SEQUENCE_NAME)
@EqualsAndHashCode(of = "id", doNotUseGetters = true)
@ToString(of = {"id", "destination", "goods"}, doNotUseGetters = true)
@Getter
public class AgreementRule implements Serializable {

    private static final long serialVersionUID = -5098286655027875691L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQ_MAIN")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "ID_DESTINATION")
    private Destination destination;

    @ManyToOne
    @JoinColumn(name = "ID_GOODS")
    private Goods goods;

    @Column(name = "REQ_ALLOWED")
    private Boolean allowed;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "rule")
    @OrderBy("VISA_RANK ASC")
    private List<AgreementRuleVisa> visas;

    @Column(name = "CREATION_DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime creationDate;

    @Column(name = "CREATION_USER")
    private String creationUser;

    @Column(name = "UPDATE_DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime updateDate;

    @Column(name = "UPDATE_USER")
    private String updateUser;


    /**
     * Build method for a new AgreementRule
     * @param id
     * @param destination
     * @param goods
     * @param allowed
     * @param creationUserUid
     * @return
     */
    public static AgreementRule build(Long id, Destination destination, Goods goods,
                                      boolean allowed, String creationUserUid) {
        AgreementRule r = new AgreementRule();
        r.id = id;
        r.version = 1;
        r.destination = destination;
        r.goods = goods;
        r.allowed = allowed;

        r.visas = new ArrayList<>();

        LocalDateTime date = LocalDateTime.now();
        r.creationDate = date;
        r.updateDate = date;

        r.creationUser = creationUserUid;
        r.updateUser = creationUserUid;

        r.checkValues();

        return r;
    }

    /**
     *
     * @param allowed
     * @param newVisas New list of visas (will replace the current list of visas) in the right order. Their rank
     *              will be recomputed
     * @param updateUser
     */
    public void update(boolean allowed, List<Pair<Department, Seniority>> newVisas, String updateUser) {
        this.allowed = allowed;
        this.updateUser = updateUser;
        this.updateDate = LocalDateTime.now();

        // It's crucial to call the "clear" method and not just calling visas = new ArrayList<>()
        this.visas.clear();
        newVisas.forEach(p -> addVisa(null, p.getLeft(), p.getRight()));

        checkValues();
        // This rule is not checked in "build" method
        checkAtLeastOneVisa();
    }

    /**
     * Convenient method to add a visa (at the end of the current list of visas)
     * @param id
     * @param department
     * @param seniority
     * @return
     */
    public AgreementRule addVisa(Long id, Department department, Seniority seniority) {
        visas.add(AgreementRuleVisa.build(this, id, visas.size(), department, seniority));
        return this;
    }

    public void checkValues() {
        checkDestination();
        checkGoods();
        checkCreationInfo();
        checkUpdateInfo();
    }

    public void checkDestination() {
        if (destination==null) throw SpTranspBizError.AGR_RULE_MISSING_VALUE.exception("destination");
    }

    public void checkGoods() {
        if (goods==null) throw SpTranspBizError.AGR_RULE_MISSING_VALUE.exception("goods");
    }

    public void checkCreationInfo() {
        if (StringUtils.isEmpty(creationUser)) throw SpTranspTechError.MISSING_INFORMATION.exception("creation user");
        if (creationDate==null) throw SpTranspTechError.MISSING_INFORMATION.exception("creation date");
    }

    public void checkUpdateInfo() {
        if (StringUtils.isEmpty(updateUser)) throw SpTranspTechError.MISSING_INFORMATION.exception("update user");
        if (updateDate==null) throw SpTranspTechError.MISSING_INFORMATION.exception("update date");
    }

    public void checkAtLeastOneVisa() {
        if (allowed && (visas==null || visas.isEmpty())) {
            throw SpTranspTechError.MISSING_INFORMATION.exception("required visas");
        }
    }
}
