package org.bdickele.sptransp.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Base repository
 * Created by Bertrand DICKELE
 */
public abstract class AbstractRepository {

    @PersistenceContext
    private EntityManager entityManager;


    protected EntityManager em() {
        return entityManager;
    }
}
