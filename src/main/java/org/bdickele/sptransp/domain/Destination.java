package org.bdickele.sptransp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Destination: planet, moon...
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name="ST_DESTINATION")
public class Destination implements Serializable {

    private static final long serialVersionUID = 7005154909722675383L;

    @Id
    private Long id;

    @Column(name = "CODE3")
    private String code3;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LABEL_ID")
    private String labelId;


    /** Constructor */
    public Destination() {
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
        Destination other = (Destination) obj;
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
