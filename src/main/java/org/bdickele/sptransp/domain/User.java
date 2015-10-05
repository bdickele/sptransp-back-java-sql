package org.bdickele.sptransp.domain;

import org.bdickele.sptransp.domain.converter.LocalDateTImeConverter;
import org.bdickele.sptransp.domain.converter.ProfileConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_USER")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "userType", discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(name="SEQ_MAIN", sequenceName="SEQ_MAIN")
public class User {

    public static final String USER_TYPE_EMPLOYEE = "E";

    public static final String USER_TYPE_CUSTOMER = "C";

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MAIN")
    protected Long id;

    @Version
    protected Integer version;

    @Column(name = "UID_USER")
    protected String uid;

    @Column(name = "USER_PASSWORD")
    protected String password;

    @Column(name = "USER_PROFILE")
    @Convert(converter = ProfileConverter.class)
    protected Profile profile;

    @Column(name = "CREATION_DATE")
    @Convert(converter = LocalDateTImeConverter.class)
    protected LocalDateTime creationDate;

    @Column(name = "CREATION_USER")
    protected String creationUser;

    @Column(name = "UPDATE_DATE")
    @Convert(converter = LocalDateTImeConverter.class)
    protected LocalDateTime updateDate;

    @Column(name = "UPDATE_USER")
    protected String updateUser;



    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Role> getRoles() {
        return profile.getRoles();
    }
}
