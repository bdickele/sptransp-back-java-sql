package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.Employee;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Employee object returned by web services
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"uid", "fullName", "profile", "departmentCode", "seniority",
        "creationDate", "creationUser", "updateDate", "updateUser"})
public class EmployeeDTO extends UserDTO implements Serializable {

    private static final long serialVersionUID = -603242397122687641L;

    @JsonProperty(value = "fullName")
    private String fullName;

    @JsonProperty(value = "departmentCode")
    private String departmentCode;

    @JsonProperty(value = "seniority")
    private Integer seniority;


    /**
     * Build method
     * @param employee
     * @return
     */
    public static EmployeeDTO build(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.uid = employee.getUid();
        dto.profile = employee.getProfile().getCode();
        dto.fullName = employee.getFullName();
        dto.departmentCode = employee.getDepartment().getCode();
        dto.seniority = employee.getSeniority().getValue();
        dto.creationDate = dto.formatDate(employee.getCreationDate());
        dto.creationUser = employee.getCreationUser();
        dto.updateDate = dto.formatDate(employee.getUpdateDate());
        dto.updateUser = employee.getUpdateUser();
        return dto;
    }

    public static List<EmployeeDTO> build(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeDTO::build)
                .collect(Collectors.toList());
    }

    public String getFullName() {
        return fullName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public Integer getSeniority() {
        return seniority;
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
        EmployeeDTO other = (EmployeeDTO) obj;
        return new EqualsBuilder()
                .append(this.uid, other.uid)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 11)
                .append(uid)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uid", uid)
                .append("fullName", fullName)
                .toString();
    }
 }
