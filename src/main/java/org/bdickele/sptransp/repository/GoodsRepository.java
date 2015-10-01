package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Goods;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
public interface GoodsRepository extends JpaRepository<Goods, Long> {

    @Cacheable("goods")
    List<Goods> findAllByOrderByNameAsc();
}