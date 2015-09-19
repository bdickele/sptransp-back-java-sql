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
@JsonPropertyOrder({"uid", "fullName", "departmentCode", "seniority", "creationDate", "creationUser"})
public class EmployeeDTO implements SpaceTranspDTO, Serializable {

    private static final long serialVersionUID = -603242397122687641L;

    @JsonProperty(value = "uid")
    private String uid;

    @JsonProperty(value = "fullName")
    private String fullName;

    @JsonProperty(value = "departmentCode")
    private String departmentCode;

    @JsonProperty(value = "seniority")
    private Integer seniority;

    @JsonProperty(value = "creationDate")
    private String creationDate;

    @JsonProperty(value = "creationUser")
    private String creationUser;


    /**
     * Build method
     * @param employee
     * @return
     */
    public static EmployeeDTO build(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.uid = employee.getUid();
        dto.fullName = employee.getFullName();
        dto.departmentCode = employee.getDepartment().getCode();
        dto.seniority = employee.getSeniority().getValue();
        dto.creationDate = dto.formatDate(employee.getCreationDate());
        dto.creationUser = employee.getCreationUser();
        return dto;
    }

    public static List<EmployeeDTO> build(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeDTO::build)
                .collect(Collectors.toList());
    }

    public String getUid() {
        return uid;
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

    public String getCreationDate() {
        return creationDate;
    }

    public String getCreationUser() {
        return creationUser;
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
