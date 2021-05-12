package org.hillel.persistence.repository;

import org.hibernate.query.criteria.internal.OrderImpl;
import org.hillel.persistence.entity.JourneyEntity_;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleEntity_;
import org.hillel.service.SearchQueryParam;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.Collection;

@Repository
public class VehicleRepository extends CommonRepository<VehicleEntity, Long> {


    protected VehicleRepository() {
        super(VehicleEntity.class);
    }

    @Override
    public Collection<VehicleEntity> findAllAsNamed() {
        return entityManager.createNamedQuery("findVehicleAll", VehicleEntity.class).getResultList();
    }

//  =================== native SQL sting query ==================================
    private String sqlMin = "select vehicle.*\n" +
            "from vehicle\n" +
            "         inner join vehicle_seat on vehicle.id = vehicle_seat.vehicle_id\n" +
            "group by vehicle.id\n" +
            "having sum(free_seats) =\n" +
            "       (select vs.free_seats\n" +
            "        from vehicle_seat vs\n" +
            "        order by vs.free_seats asc\n" +
            "        limit 1);";

    private String sqlMax = "select vehicle.*\n" +
            "from vehicle\n" +
            "         inner join vehicle_seat on vehicle.id = vehicle_seat.vehicle_id\n" +
            "group by vehicle.id\n" +
            "having sum(free_seats) =\n" +
            "       (select vs.free_seats\n" +
            "        from vehicle_seat vs\n" +
            "        order by vs.free_seats desc\n" +
            "        limit 1);";

    //  =================== native SQL sting query with number of free seats ====
    private String sqlMax2 = "select vehicle.*, vehicle_seat.free_seats\n" +
            "from vehicle\n" +
            "         inner join vehicle_seat on vehicle.id = vehicle_seat.vehicle_id\n" +
            "order by vehicle_seat.free_seats desc limit 1";


    private String sqlMin2 = "select vehicle.*, vehicle_seat.free_seats\n" +
            "from vehicle\n" +
            "         inner join vehicle_seat on vehicle.id = vehicle_seat.vehicle_id\n" +
            "group by vehicle.id, vehicle_seat.free_seats\n" +
            "having sum(free_seats) =\n" +
            "       (select vs.free_seats\n" +
            "        from vehicle_seat vs\n" +
            "        order by vs.free_seats asc\n" +
            "        limit 1)";


    public Collection<VehicleEntity> findVehicleWithMinSeats() {
        return entityManager.createNativeQuery(sqlMin2, VehicleEntity.class).getResultList();
    }

    public Collection<VehicleEntity> findVehicleWithMaxSeats() {
        return entityManager.createNativeQuery(sqlMax2, VehicleEntity.class).getResultList();
    }



    /*@Override
    public Collection<VehicleEntity> findAllVehicles(SearchQueryParam searchQueryParam) {
        return super.findAllAsCriteriaBuildWithParams(searchQueryParam);
    }*/

    /*public Collection<VehicleEntity> findAllPagebleVehicles() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<VehicleEntity> query = criteriaBuilder.createQuery(VehicleEntity.class);
        final Root<VehicleEntity> from = query.from(VehicleEntity.class);
        final Join<Object, Object> journeys = from.join(VehicleEntity_.JOURNEYS, JoinType.LEFT);
        final Predicate byJourneyName = criteriaBuilder.equal(journeys.get(JourneyEntity_.STATION_FROM), criteriaBuilder.parameter(String.class, "stationFromParam"));
        journeys.on(byJourneyName);
        //final Predicate byName = criteriaBuilder.equal(from.get(VehicleEntity_.NAME), criteriaBuilder.parameter(String.class, "nameParam"));
        final Predicate active = criteriaBuilder.equal(from.get(VehicleEntity_.ACTIVE), criteriaBuilder.parameter(Boolean.class, "activeParam"));

        return entityManager.createQuery(query
                        .select(from)
                        //.where(byName, active).orderBy(new OrderImpl(from.get(VehicleEntity_.ID), false)) // здесь сортировка, false соотв. desc
                       // .where(active).orderBy(new OrderImpl(from.get(VehicleEntity_.ID), true)) // здесь сортировка, false соотв. desc
                        .where(active).orderBy(new OrderImpl(from.get(VehicleEntity_.NAME), true)) // здесь сортировка, false соотв. desc
                // в new OrderImpl передаем параметр, по которому сортируем, здесь это по полю id. Это аналог v.id.
        )
                //.setParameter("nameParam", name)
                .setParameter("activeParam", true)
                .setParameter("stationFromParam", "from 1")
                .setFirstResult(0)
                .setMaxResults(4)
                .getResultList();
    }*/
}