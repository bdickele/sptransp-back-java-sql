package org.bdickele.sptransp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.bdickele.sptransp.domain.converter.LocalDateTimeConverter;
import org.bdickele.sptransp.domain.converter.ProfileConverter;
import org.bdickele.sptransp.exception.SpTranspTechError;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_USER")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(name="SEQ_MAIN", sequenceName=DomainConst.SEQUENCE_USER, allocationSize = DomainConst.SEQ_ALLOCATION_SIZE)
@EqualsAndHashCode(of = "id", doNotUseGetters = true)
@Getter
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
    protected UserProfile profile;

    @Column(name = "CREATION_DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    protected LocalDateTime creationDate;

    @Column(name = "CREATION_USER")
    protected String creationUser;

    @Column(name = "UPDATE_DATE")
    @Convert(converter = LocalDateTimeConverter.class)
    protected LocalDateTime updateDate;

    @Column(name = "UPDATE_USER")
    protected String updateUser;


    public void checkCreationInfo() {
        if (StringUtils.isEmpty(creationUser)) throw SpTranspTechError.MISSING_INFORMATION.exception("creation user");
        if (creationDate==null) throw SpTranspTechError.MISSING_INFORMATION.exception("creation date");
    }

    public void checkUpdateInfo() {
        if (StringUtils.isEmpty(updateUser)) throw SpTranspTechError.MISSING_INFORMATION.exception("update user");
        if (updateDate==null) throw SpTranspTechError.MISSING_INFORMATION.exception("update date");
    }
}
