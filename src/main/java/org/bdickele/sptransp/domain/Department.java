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
@Table(name = "ST_DEPARTMENT")
public class Department implements Serializable {

    private static final long serialVersionUID = 8469548980602140778L;

    @Id
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LABEL_ID")
    private String labelId;


    /** Constructor */
    public Department() {
    }

    /**
     * Build method for a Department
     * @param id
     * @param name
     * @param labelId
     * @return
     */
    public static Department buidl(Long id, String name, String labelId) {
        Department d = new Department();
        d.id = id;
        d.name = name;
        d.labelId = labelId;
        return d;
    }

    public Long getId() {
        return id;
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
        Department other = (Department) obj;
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
                .append("name", name)
                .toString();
    }
}
