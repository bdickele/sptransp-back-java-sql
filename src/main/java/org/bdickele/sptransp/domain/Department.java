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

    @Column(name = "CODE")
    private String code;


    /** Constructor */
    public Department() {
    }

    /**
     * Build method for a Department
     * @param id
     * @param code
     * @param name
     * @return
     */
    public static Department buidl(Long id, String code, String name) {
        Department d = new Department();
        d.id = id;
        d.name = name;
        d.code = code;
        return d;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
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
                .append("code", code)
                .append("name", name)
                .toString();
    }
}
