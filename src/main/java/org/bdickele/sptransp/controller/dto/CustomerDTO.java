package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.Customer;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"uid", "fullName", "profileCode", "profileLabel",
        "creationDate", "creationUser", "updateDate", "updateUser"})
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
        dto.profileLabel = customer.getProfile().getLabel();
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

    public String getFullName() {
        return fullName;
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
        CustomerDTO other = (CustomerDTO) obj;
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
