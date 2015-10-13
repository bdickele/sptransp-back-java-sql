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
 * Destination: planet, moon...
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name="ST_DESTINATION")
@EqualsAndHashCode(of = "id", doNotUseGetters = true)
@ToString(of = {"id", "code", "name"}, doNotUseGetters = true)
public class Destination implements Serializable {

    private static final long serialVersionUID = 7005154909722675383L;

    @Id
    @Getter private Long id;

    @Column(name = "CODE")
    @Getter private String code;

    @Column(name = "NAME")
    @Getter private String name;


    /**
     * Build method for a destination
     * @param id
     * @param code
     * @param name
     * @return
     */
    public static Destination build(Long id, String code, String name) {
        Destination d = new Destination();
        d.id = id;
        d.code = code;
        d.name = name;
        return d;
    }
}
