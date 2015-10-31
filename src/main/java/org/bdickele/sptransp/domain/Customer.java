package org.bdickele.sptransp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bdickele.sptransp.exception.SpTranspBizError;
import org.bdickele.sptransp.exception.SpTranspTechError;
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
@EqualsAndHashCode(callSuper=true, of={}, doNotUseGetters = true)
@Getter
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
        c.version = 1;
        c.password = passwordEncoder.encode("changeme");
        c.uid = uid;
        c.profile = UserProfile.CUSTOMER;
        c.fullName = fullName;

        LocalDateTime date = LocalDateTime.now();
        c.creationDate = date;
        c.updateDate = date;

        c.creationUser = creationUserUid;
        c.updateUser = creationUserUid;

        c.checkValues();

        return c;
    }

    public void update(String fullName, String updateUser) {
        this.fullName = fullName;
        this.updateUser = updateUser;
        this.updateDate = LocalDateTime.now();

        checkValues();
    }

    public void checkValues() {
        checkUid();
        checkFullName();
        checkCreationInfo();
        checkUpdateInfo();
    }

    public void checkUid() {
        if (StringUtils.isEmpty(uid)) throw SpTranspBizError.CUSTOMER_MISSING_VALUE.exception("uid");
    }

    public void checkFullName() {
        if (StringUtils.isEmpty(fullName)) throw SpTranspBizError.CUSTOMER_MISSING_VALUE.exception("name");
    }

    public void checkCreationInfo() {
        if (StringUtils.isEmpty(creationUser)) throw SpTranspTechError.MISSING_INFORMATION.exception("creation user");
        if (creationDate==null) throw SpTranspTechError.MISSING_INFORMATION.exception("creation date");
    }

    public void checkUpdateInfo() {
        if (StringUtils.isEmpty(updateUser)) throw SpTranspTechError.MISSING_INFORMATION.exception("update user");
        if (updateDate==null) throw SpTranspTechError.MISSING_INFORMATION.exception("update date");
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("uid", uid)
                .append("fullName", fullName)
                .toString();
    }
}
