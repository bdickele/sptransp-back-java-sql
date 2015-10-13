package org.bdickele.sptransp.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name = "ST_GOODS")
@EqualsAndHashCode(of = "id", doNotUseGetters = true)
@ToString(of = {"id", "code", "name"}, doNotUseGetters = true)
@Getter
public class Goods implements Serializable {

    private static final long serialVersionUID = -2194079517457892877L;

    @Id
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;


    /**
     * Build method for Goods
     * @param id
     * @param code
     * @param name
     * @return
     */
    public static Goods build(Long id, String code, String name) {
        Goods g = new Goods();
        g.id = id;
        g.code = code;
        g.name = name;
        return g;
    }
}
