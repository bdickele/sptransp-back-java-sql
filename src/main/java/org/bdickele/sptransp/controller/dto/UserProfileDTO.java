package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.UserProfile;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"code", "label"})
public class UserProfileDTO implements Serializable {

    private static final long serialVersionUID = 463285960555927346L;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "label")
    private String label;

    /**
     * Build method
     * @param profile
     * @return
     */
    public static UserProfileDTO build(UserProfile profile) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.code = profile.getCode();
        dto.label = profile.getLabel();
        return dto;
    }

    public static List<UserProfileDTO> build(List<UserProfile> profiles) {
        return profiles.stream()
                .map(UserProfileDTO::build)
                .collect(Collectors.toList());
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
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
        UserProfileDTO other = (UserProfileDTO) obj;
        return new EqualsBuilder()
                .append(this.code, other.code)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 11)
                .append(code)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("label", label)
                .toString();
    }
}
