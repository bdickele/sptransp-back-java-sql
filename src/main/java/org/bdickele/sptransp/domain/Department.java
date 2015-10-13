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
@Table(name = "ST_DEPARTMENT")
@EqualsAndHashCode(of = "id", doNotUseGetters = true)
@ToString(of = {"id", "code", "name"}, doNotUseGetters = true)
@Getter
public class Department implements Serializable {

    private static final long serialVersionUID = 8469548980602140778L;

    @Id
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;


    /**
     * Build method for a Department
     * @param id
     * @param code
     * @param name
     * @return
     */
    public static Department buidl(Long id, String code, String name) {
        Department d = new Department();
        d.id = id;
        d.name = name;
        d.code = code;
        return d;
    }
}
