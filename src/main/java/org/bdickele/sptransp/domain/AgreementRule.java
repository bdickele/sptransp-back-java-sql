package org.bdickele.sptransp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
@SequenceGenerator(name="SEQ_MAIN", sequenceName="SEQ_MAIN")
public class AgreementRule implements Serializable {

    private static final long serialVersionUID = -5098286655027875691L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQ_MAIN")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "DELETED")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "ID_DESTINATION")
    private Destination destination;

    @ManyToOne
    @JoinColumn(name = "ID_GOOD")
    private Good good;

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
     * @param destination
     * @param good
     * @param creationUserUid
     * @return
     */
    public static AgreementRule build(Long id, Destination destination, Good good, String creationUserUid) {
        AgreementRule r = new AgreementRule();
        r.id = id;
        r.version = 0;
        r.deleted = false;
        r.destination = destination;
        r.good = good;

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

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public List<AgreementRuleVisa> getVisas() {
        return visas;
    }

    public void setVisas(List<AgreementRuleVisa> visas) {
        this.visas = visas;
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
        AgreementRule other = (AgreementRule) obj;
        return new EqualsBuilder()
                .append(this.id, other.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 11)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("destination", destination.getName())
                .append("good", good.getName())
                .toString();
    }
}