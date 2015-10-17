package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Bertrand DICKELE
 */
public interface GoodsRepository extends JpaRepository<Goods, Long> {

    List<Goods> findAllByOrderByNameAsc();

    Goods findByCode(String code);
}
