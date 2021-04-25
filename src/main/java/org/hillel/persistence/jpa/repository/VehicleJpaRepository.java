package org.hillel.persistence.jpa.repository;

import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface VehicleJpaRepository extends CrudRepository<VehicleEntity, Long> {

    //    Collection<VehicleEntity> findByName(String name);
    Collection<VehicleEntity> findByNameAndActiveIsTrue(String name);
    //    Collection<VehicleEntity> findDistinctFirs7tByNameAndActiveIsTrue(String name);

}
