package org.bdickele.sptransp.domain.audit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Bertrand DICKELE
 */
@Embeddable
@EqualsAndHashCode(of = {"id", "version"}, doNotUseGetters = true)
@ToString(of = {"id", "version"}, doNotUseGetters = true)
@Getter
public class EmployeeAudPK implements Serializable {

    private static final long serialVersionUID = 584182696871123483L;

    private Long id;

    private Integer version;


    public static EmployeeAudPK build(Long id, Integer version) {
        EmployeeAudPK pk = new EmployeeAudPK();
        pk.id = id;
        pk.version = version;
        return pk;
    }
}
