package org.hillel.persistence.repository;


import org.hillel.persistence.entity.VehicleFreeSeatsEntity;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleSeatsRepository extends CommonRepository<VehicleFreeSeatsEntity, Long> {

    public VehicleSeatsRepository() {
        super(VehicleFreeSeatsEntity.class);
    }


}
