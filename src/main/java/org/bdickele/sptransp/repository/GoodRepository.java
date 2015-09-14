package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Good;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
public interface GoodRepository extends JpaRepository<Good, Long> {

    @Override
    @Cacheable("goods")
    List<Good> findAll();
}
