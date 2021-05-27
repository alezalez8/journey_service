package org.hillel.persistence.jpa.repository;

import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

//public interface  VehicleJpaRepository extends CrudRepository<VehicleEntity, Long> { // до CommonJpaRepository
//public interface  VehicleJpaRepository extends CommonJpaRepository<VehicleEntity, Long>, CrudRepository<VehicleEntity, Long> {

public interface VehicleJpaRepository extends CommonJpaRepository<VehicleEntity, Long>,
        JpaSpecificationExecutor<VehicleEntity> {



    Collection<VehicleEntity> searchByName(String name);

    Collection<VehicleEntity> findDistinctByNameAndActiveIsTrue(String name);

    @Query(value = "select v.* from vehicle v where v.id between :id_from and :id_to and v.name = :name",
            countQuery = "select count(v.id) from  vehicle v", nativeQuery = true)

    Page<VehicleEntity> findByConditions(@Param("name") String name,
                                         @Param("id_from") Long idFrom,
                                         @Param("id_to") Long idTo, Pageable page);


    List<SimpleVehicleDto> findAllByActiveIsTrue();

}
