package org.bdickele.sptransp.controller.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bdickele.sptransp.domain.Destination;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Destination object returned by web services
 * Created by Bertrand DICKELE
 */
@EqualsAndHashCode(of = "code", doNotUseGetters = true)
@ToString(of = {"code", "name"}, doNotUseGetters = true)
@Getter
public class DestinationDTO implements SpaceTranspDTO, Serializable {

    private static final long serialVersionUID = -4111120841917837925L;

    private String code;

    private String name;

    private String comment;


    /**
     * Build method
     * @param destination
     * @return
     */
    public static DestinationDTO build(Destination destination) {
        DestinationDTO dto = new DestinationDTO();
        dto.code = destination.getCode();
        dto.name = destination.getName();
        dto.comment = destination.getComment();
        return dto;
    }

    public static List<DestinationDTO> build(List<Destination> destinations) {
        return destinations.stream()
                .map(DestinationDTO::build)
                .collect(Collectors.toList());
    }
}
