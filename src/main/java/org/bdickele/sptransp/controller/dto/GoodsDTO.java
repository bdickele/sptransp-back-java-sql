package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bdickele.sptransp.domain.Goods;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Goods object returned by web services
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"code", "name"})
public class GoodsDTO implements SpaceTranspDTO, Serializable {

    private static final long serialVersionUID = -7786611351850896451L;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "name")
    private String name;


    /**
     * Build method
     * @param goods
     * @return
     */
    public static GoodsDTO build(Goods goods) {
        GoodsDTO dto = new GoodsDTO();
        dto.code = goods.getCode();
        dto.name = goods.getName();
        return dto;
    }

    public static List<GoodsDTO> build(List<Goods> goodses) {
        return goodses.stream()
                .map(GoodsDTO::build)
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
        GoodsDTO other = (GoodsDTO) obj;
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
