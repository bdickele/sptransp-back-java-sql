package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.configuration.DomainCacheConfig;
import org.bdickele.sptransp.domain.Goods;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
public interface GoodsRepository extends JpaRepository<Goods, Long> {

    @Cacheable(DomainCacheConfig.GOODS)
    List<Goods> findAllByOrderByNameAsc();
}
