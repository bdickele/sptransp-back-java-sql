package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.configuration.DomainCacheConfig;
import org.bdickele.sptransp.domain.Destination;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by bdickele
 */
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    List<Destination> findAllByOrderByNameAsc();

    /**
     * @param code Destination's code
     * @return Corresponding Destination
     */
    @Cacheable(value= DomainCacheConfig.DESTINATION)
    Destination findByCode(String code);
}
