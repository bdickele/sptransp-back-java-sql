package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Bertrand DICKELE
 */
public abstract class UserDTO implements SpaceTranspDTO {

    @JsonProperty(value = "uid")
    protected String uid;

    @JsonProperty(value = "profileCode")
    protected String profileCode;

    @JsonProperty(value = "profileLabel")
    protected String profileLabel;

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

    public String getProfileCode() {
        return profileCode;
    }
}
