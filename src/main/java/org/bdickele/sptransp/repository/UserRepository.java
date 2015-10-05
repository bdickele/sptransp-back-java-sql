package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bdickele
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUid(String uid);
}
