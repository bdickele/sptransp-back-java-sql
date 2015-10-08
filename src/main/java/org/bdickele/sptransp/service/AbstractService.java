package org.bdickele.sptransp.service;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Created by Bertrand DICKELE
 */
public class AbstractService {

    @Autowired
    private EntityManager entityManager;


    public EntityManager em() {
        return entityManager;
    }

}
