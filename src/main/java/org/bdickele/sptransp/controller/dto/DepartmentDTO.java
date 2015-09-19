package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.Department;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"code", "name"})
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
        DepartmentDTO other = (DepartmentDTO) obj;
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
