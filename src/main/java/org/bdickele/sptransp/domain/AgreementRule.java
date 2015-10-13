package org.bdickele.sptransp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Cacheable
@Table(name = "ST_AGREEMENT_RULE")
@SequenceGenerator(name="SEQ_MAIN", sequenceName="SEQ_MAIN")
@EqualsAndHashCode(of = "id", doNotUseGetters = true)
@ToString(of = {"id", "destinationId", "goodsId"}, doNotUseGetters = true)
@Getter
public class AgreementRule implements Serializable {

    private static final long serialVersionUID = -5098286655027875691L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQ_MAIN")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "ID_DESTINATION")
    private Long destinationId;

    @Column(name = "ID_GOODS")
    private Long goodsId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_RULE")
    @OrderBy("RANK ASC")
    private List<AgreementRuleVisa> visas;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "CREATION_USER")
    private String creationUser;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "UPDATE_USER")
    private String updateUser;


    /**
     * Build method for a new AgreementRule
     * @param id
     * @param destinationId
     * @param goodsId
     * @param creationUserUid
     * @return
     */
    public static AgreementRule build(Long id, Long destinationId, Long goodsId, String creationUserUid) {
        AgreementRule r = new AgreementRule();
        r.id = id;
        r.version = 0;
        r.destinationId = destinationId;
        r.goodsId = goodsId;

        r.visas = new ArrayList<>();

        LocalDateTime date = LocalDateTime.now();
        r.creationDate = date;
        r.updateDate = date;

        r.creationUser = creationUserUid;
        r.updateUser = creationUserUid;

        return r;
    }

    /**
     * Convenient method to add a visa (at the end of the current list of visas)
     * @param id
     * @param department
     * @param seniority
     * @return
     */
    public AgreementRule addVisa(Long id, Department department, Seniority seniority) {
        visas.add(AgreementRuleVisa.build(id, visas.size(), department, seniority));
        return this;
    }
}
