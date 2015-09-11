package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bdickele
 */
public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
