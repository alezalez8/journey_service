package org.hillel.persistence.repository;


import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public class StopRepository extends CommonRepository<StopEntity, Long> {

    protected StopRepository() {
        super(StopEntity.class);
    }

    @Override
    public Collection<StopEntity> findAllAsNamed() {
        return entityManager.createNamedQuery("findStopAll", StopEntity.class).getResultList();
    }

}