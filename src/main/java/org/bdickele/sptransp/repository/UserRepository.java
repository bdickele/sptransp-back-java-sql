package org.bdickele.sptransp.repository;

import org.bdickele.sptransp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bdickele
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUid(String uid);

    @Query("select u.uid from User u where uid like :uidPrefix%")
    List<String> findUidsStartingWith(@Param("uidPrefix") String uidPrefix);
}
