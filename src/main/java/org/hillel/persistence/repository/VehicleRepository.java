package org.hillel.persistence.repository;

import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class VehicleRepository extends CommonRepository<VehicleEntity, Long> {


    protected VehicleRepository() {
        super(VehicleEntity.class);
    }

    @Override
    public Collection<VehicleEntity> findAllAsNamed(){
        return  entityManager.createNamedQuery("findVehicleAll", VehicleEntity.class).getResultList();
    }

}