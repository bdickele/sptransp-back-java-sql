package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.Employee;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Employee object returned by web services
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"uid", "fullName", "profileCode", "profileName", "departmentCode", "departmentName",
        "seniority", "creationDate", "creationUser", "updateDate", "updateUser"})
@EqualsAndHashCode(callSuper = true, of = {}, doNotUseGetters = true)
@Getter
public class EmployeeDTO extends UserDTO implements Serializable {

    private static final long serialVersionUID = -603242397122687641L;

    private String fullName;

    private String departmentCode;

    private String departmentName;

    private Integer seniority;


    /**
     * Build method
     * @param employee
     * @return
     */
    public static EmployeeDTO build(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.uid = employee.getUid();
        dto.profileCode = employee.getProfile().getCode();
        dto.profileName = employee.getProfile().getLabel();
        dto.fullName = employee.getFullName();
        dto.departmentCode = employee.getDepartment().getCode();
        dto.departmentName = employee.getDepartment().getName();
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uid", uid)
                .append("fullName", fullName)
                .toString();
    }
 }
