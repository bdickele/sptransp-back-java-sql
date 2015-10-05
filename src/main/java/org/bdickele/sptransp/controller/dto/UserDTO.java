package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Bertrand DICKELE
 */
public abstract class UserDTO implements SpaceTranspDTO {

    @JsonProperty(value = "uid")
    protected String uid;

    @JsonProperty(value = "profile")
    protected String profile;

    @JsonProperty(value = "creationDate")
    protected String creationDate;

    @JsonProperty(value = "creationUser")
    protected String creationUser;

    @JsonProperty(value = "updateDate")
    protected String updateDate;

    @JsonProperty(value = "updateUser")
    protected String updateUser;


    public String getUid() {
        return uid;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }
}
