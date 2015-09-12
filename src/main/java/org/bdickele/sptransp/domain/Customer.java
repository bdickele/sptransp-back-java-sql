package org.bdickele.sptransp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_CUSTOMER")
@SequenceGenerator(name="SEQ_MAIN", sequenceName="SEQ_MAIN")
public class Customer implements Serializable {

    private static final long serialVersionUID = 4802565099997858014L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MAIN")
    private Long id;

    @Column(name = "UID")
    private String uid;

    @Column(name = "FULL_NAME")
    private String fullName;


    /** Constructor */
    public Customer() {
        //
    }

    /**
     * Build method
     * @param id
     * @param uid
     * @param fullName
     * @return New Customer
     */
    public static Customer build(Long id, String uid, String fullName) {
        Customer c = new Customer();
        c.id = id;
        c.uid = uid;
        c.fullName = fullName;
        return c;
    }

    public Long getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getFullName() {
        return fullName;
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
        Customer other = (Customer) obj;
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
                .append("uid", uid)
                .append("fullName", fullName)
                .toString();
    }
}
