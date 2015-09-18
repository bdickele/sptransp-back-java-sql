package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.Destination;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"code", "name"})
public class DestinationDTO implements Serializable {

    private static final long serialVersionUID = -4111120841917837925L;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "name")
    private String name;


    /**
     * Build method
     * @param destination
     * @return
     */
    public static DestinationDTO build(Destination destination) {
        DestinationDTO dto = new DestinationDTO();
        dto.code = destination.getCode();
        dto.name = destination.getName();
        return dto;
    }

    public static List<DestinationDTO> build(List<Destination> destinations) {
        return destinations.stream()
                .map(DestinationDTO::build)
                .collect(Collectors.toList());
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
        DestinationDTO other = (DestinationDTO) obj;
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
                .append("name", name)
                .toString();
    }
}
