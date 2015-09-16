package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Destination;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by bdickele
 */
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    @Override
    @Cacheable("destinations")
    List<Destination> findAll();

    /**
     * @param code Destination's code
     * @return Corresponding Destination
     */
    Destination findByCode(String code);
}
