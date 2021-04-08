package org.hillel.persistence.repository;


import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class StopRepository extends CommonRepository<StopEntity, Long> {


    protected StopRepository() {
        super(StopEntity.class);
    }



}
