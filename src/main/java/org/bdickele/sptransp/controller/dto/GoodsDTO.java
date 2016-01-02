package org.bdickele.sptransp.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bdickele.sptransp.domain.Goods;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Goods object returned by web services
 * Created by Bertrand DICKELE
 */
@JsonPropertyOrder({"code", "name"})
@EqualsAndHashCode(of = "code", doNotUseGetters = true)
@ToString(of = {"code", "name"}, doNotUseGetters = true)
@Getter
public class GoodsDTO implements SpaceTranspDTO, Serializable {

    private static final long serialVersionUID = -7786611351850896451L;

    private String code;

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
}
