package org.hillel.persistence.repository;


import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleFreeSeatsEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class VehicleSeatsRepository extends CommonRepository<VehicleFreeSeatsEntity, Long> {

    public VehicleSeatsRepository() {
        super(VehicleFreeSeatsEntity.class);
    }

    @Override
    public Collection<VehicleFreeSeatsEntity> findAllAsNamed() {
        return entityManager.createNamedQuery("findFreeSeatsAll", VehicleFreeSeatsEntity.class).getResultList();
    }
}
