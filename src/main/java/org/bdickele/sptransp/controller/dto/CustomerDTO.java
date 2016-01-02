package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.Customer;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"uid", "fullName", "profileCode", "profileName",
        "creationDate", "creationUser", "updateDate", "updateUser"})
@EqualsAndHashCode(callSuper = true, of = {}, doNotUseGetters = true)
@Getter
public class CustomerDTO extends UserDTO implements Serializable {

    private static final long serialVersionUID = -8182869212493007142L;

    @JsonProperty(value = "fullName")
    private String fullName;


    /**
     * Build method
     * @param customer
     * @return
     */
    public static CustomerDTO build(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.uid = customer.getUid();
        dto.profileCode = customer.getProfile().getCode();
        dto.profileName = customer.getProfile().getLabel();
        dto.fullName = customer.getFullName();
        dto.creationDate = dto.formatDate(customer.getCreationDate());
        dto.creationUser = customer.getCreationUser();
        dto.updateDate = dto.formatDate(customer.getUpdateDate());
        dto.updateUser = customer.getUpdateUser();
        return dto;
    }

    public static List<CustomerDTO> build(List<Customer> customers) {
        return customers.stream()
                .map(CustomerDTO::build)
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
