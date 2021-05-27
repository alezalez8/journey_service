package org.hillel.persistence.jpa.repository;

import org.hillel.persistence.entity.VehicleFreeSeatsEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VehicleFreeSeatJpaRepository extends CommonJpaRepository<VehicleFreeSeatsEntity, Long>,
        JpaSpecificationExecutor<VehicleFreeSeatsEntity> {
}
