package org.bdickele.sptransp.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Destination: planet, moon...
 * Created by Bertrand DICKELE
 */
@Entity
@Table(name="DESTINATION")
public class Destination implements Serializable {

    private static final long serialVersionUID = 7005154909722675383L;

    @Id
    private Long id;

    @Column(name = "NAME")
    private String name;
}
