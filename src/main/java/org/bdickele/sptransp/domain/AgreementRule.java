package org.bdickele.sptransp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bdickele.sptransp.domain.converter.LocalDateTimeConverter;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_RULE")
    @OrderBy("RANK ASC")
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
     * @param creationUserUid
     * @return
     */
    public static AgreementRule build(Long id, Destination destination, Goods goods, String creationUserUid) {
        AgreementRule r = new AgreementRule();
        r.id = id;
        r.version = 0;
        r.destination = destination;
        r.goods = goods;
        r.allowed = true;

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
