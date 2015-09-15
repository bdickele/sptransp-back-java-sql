package org.bdickele.sptransp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_GOOD")
public class Goods implements Serializable {

    private static final long serialVersionUID = -2194079517457892877L;

    @Id
    private Long id;

    @Column(name = "CODE3")
    private String code3;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LABEL_ID")
    private String labelId;


    /** Constructor */
    public Goods() {
    }

    public static Goods build(Long id, String code3, String name, String labelId) {
        Goods g = new Goods();
        g.id = id;
        g.code3 = code3;
        g.name = name;
        g.labelId = labelId;
        return g;
    }

    public Long getId() {
        return id;
    }

    public String getCode3() {
        return code3;
    }

    public String getName() {
        return name;
    }

    public String getLabelId() {
        return labelId;
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
        Goods other = (Goods) obj;
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
                .append("code3", code3)
                .append("name", name)
                .toString();
    }
}
