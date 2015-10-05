package org.bdickele.sptransp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_CUSTOMER")
@DiscriminatorValue(User.USER_TYPE_CUSTOMER)
public class Customer extends User implements Serializable {

    private static final long serialVersionUID = 4802565099997858014L;

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
     * @param creationUserUid
     * @param passwordEncoder
     * @return New Customer
     */
    public static Customer build(Long id, String uid, String fullName, String creationUserUid, PasswordEncoder passwordEncoder) {
        Customer c = new Customer();
        c.id = id;
        c.version = 0;
        c.password = passwordEncoder.encode("changeme");
        c.uid = uid;
        c.profile = Profile.CUSTOMER;
        c.fullName = fullName;

        LocalDateTime date = LocalDateTime.now();
        c.creationDate = date;
        c.updateDate = date;

        c.creationUser = creationUserUid;
        c.updateUser = creationUserUid;

        return c;
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
