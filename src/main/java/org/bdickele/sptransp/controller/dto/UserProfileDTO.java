package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bdickele.sptransp.domain.UserProfile;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"code", "label"})
@EqualsAndHashCode(of = "code", doNotUseGetters = true)
@ToString(of = {"code", "label"}, doNotUseGetters = true)
@Getter
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
}
