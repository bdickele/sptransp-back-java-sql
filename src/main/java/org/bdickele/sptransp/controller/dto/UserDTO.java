package org.bdickele.sptransp.controller.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by Bertrand DICKELE
 */
@EqualsAndHashCode(of = "uid", doNotUseGetters = true)
@Getter
public abstract class UserDTO implements SpaceTranspDTO {

    protected String uid;

    protected String profileCode;

    protected String profileName;

    protected String creationDate;

    protected String creationUser;

    protected String updateDate;

    protected String updateUser;

}
