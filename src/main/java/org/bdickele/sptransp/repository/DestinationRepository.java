package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bdickele
 */
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    /**
     * @param code3 Destination's code3
     * @return Corresponding Destination
     */
    Destination findByCode3(String code3);
}
