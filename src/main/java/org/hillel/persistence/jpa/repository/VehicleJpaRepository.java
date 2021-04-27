package org.hillel.persistence.jpa.repository;

import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface VehicleJpaRepository extends CrudRepository<VehicleEntity, Long> {

    //    Collection<VehicleEntity> findByName(String name);
    Collection<VehicleEntity> findByNameAndActiveIsTrue(String name);
    //    Collection<VehicleEntity> findDistinctFirs7tByNameAndActiveIsTrue(String name);

    // @Query("select v from VehicleEntity v where v.id between ?2 and ?3 and v.name = ?1")   // без анализа семантики, не анализирует имя findByConditions
    // Collection<VehicleEntity> findByConditions(String name, Long idFrom, Long idTo);

    @Query("select v from VehicleEntity v where v.id between :id_from and :id_to and v.name = :name")  // через имена, но нужно доуказать @Param
    Collection<VehicleEntity> findByConditions(@Param("name") String name,
                                               @Param("id_from") Long idFrom,
                                               @Param("id_to") Long idTo);

}
