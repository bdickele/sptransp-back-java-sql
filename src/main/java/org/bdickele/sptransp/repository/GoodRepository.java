package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bertrand DICKELE
 */
public interface GoodRepository extends JpaRepository<Good, Long> {
}
