package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bdickele.sptransp.domain.Department;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"code", "name"})
@EqualsAndHashCode(of = "code", doNotUseGetters = true)
@ToString(of = {"code", "name"}, doNotUseGetters = true)
@Getter
public class DepartmentDTO implements SpaceTranspDTO, Serializable {

    private static final long serialVersionUID = 6129408205740913875L;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "name")
    private String name;


    /**
     * Build method
     * @param department
     * @return
     */
    public static DepartmentDTO build(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.code = department.getCode();
        dto.name = department.getName();
        return dto;
    }

    public static List<DepartmentDTO> build(List<Department> departments) {
        return departments.stream()
                .map(DepartmentDTO::build)
                .collect(Collectors.toList());
    }
}
