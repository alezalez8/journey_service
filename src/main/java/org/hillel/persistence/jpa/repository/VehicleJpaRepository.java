package org.hillel.persistence.jpa.repository;

import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;


public interface VehicleJpaRepository extends CommonJpaRepository<VehicleEntity, Long>,
        JpaSpecificationExecutor<VehicleEntity> {

    Collection<VehicleEntity> findByName(String name);


    Collection<VehicleEntity> findDistinctByNameAndActiveIsTrue(String name);


    @Query(value = "select v.* from vehicle v where v.id between :id_from and :id_to and v.name = :name",
            countQuery = "select count(v.id) from  vehicle v", nativeQuery = true)
    List<VehicleEntity> findByConditions(@Param("name") String name,
                                         @Param("id_from") Long idFrom,
                                         @Param("id_to") Long idTo, Pageable page);


    List<SimpleVehicleDto> findAllByActiveIsTrue();


    //  =================== native SQL sting query with number of free seats ====
    String sqlMax2 = "select vehicle.*, vehicle_seat.free_seats\n" +
            "from vehicle\n" +
            "         inner join vehicle_seat on vehicle.id = vehicle_seat.vehicle_id\n" +
            "order by vehicle_seat.free_seats desc limit 1";


    String sqlMin2 = "select vehicle.*, vehicle_seat.free_seats\n" +
            "from vehicle\n" +
            "         inner join vehicle_seat on vehicle.id = vehicle_seat.vehicle_id\n" +
            "group by vehicle.id, vehicle_seat.free_seats\n" +
            "having sum(free_seats) =\n" +
            "       (select vs.free_seats\n" +
            "        from vehicle_seat vs\n" +
            "        order by vs.free_seats asc\n" +
            "        limit 1)";

    @Query(value = sqlMax2, nativeQuery = true)
    Collection<VehicleEntity> findVehicleWithMinSeats();

    @Query(value = sqlMin2, nativeQuery = true)
    Collection<VehicleEntity> findVehicleWithMaxSeats();


}