package org.hillel.persistence.repository;

import org.hillel.persistence.entity.VehicleEntity;

public class VehicleRepository extends CommonRepository<VehicleEntity, Long> {

    protected VehicleRepository() {
        super(VehicleEntity.class );
    }
}
