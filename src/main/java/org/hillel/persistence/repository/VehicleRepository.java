package org.hillel.persistence.repository;

import org.hibernate.query.criteria.internal.OrderImpl;
import org.hillel.persistence.entity.JourneyEntity_;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleEntity_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Optional;

@Repository
public class VehicleRepository extends CommonRepository<VehicleEntity, Long> {


    protected VehicleRepository() {
        super(VehicleEntity.class);
    }

    @Override
    public void remove(VehicleEntity entity) {
        entity = findById(entity.getId()).get();
        //entity.removeAllJourney();
        super.remove(entity);
    }

    //  ===== это для критерия запроса  в CommonRepository  ===============================
    /*@Override
    public Collection<VehicleEntity> findAll() {
        System.out.println("call from veh repo");
        //return entityManager.createNamedQuery("findAll", VehicleEntity.class).getResultList(); // вызов через @NamedQueries(value = {...
        return entityManager.createNamedStoredProcedureQuery("findAllVehicle").getResultList();
    }*/

    /*@Override
    public Collection<VehicleEntity> findByName(String name) {
        return entityManager.createQuery("from VehicleEntity e where e.name");
    }*/

    public Collection<VehicleEntity> findByName(String name) {

        /*final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<VehicleEntity> query = criteriaBuilder.createQuery(VehicleEntity.class);
        final Root<VehicleEntity> from = query.from(VehicleEntity.class);
        final Join<Object, Object> journeys = from.join(VehicleEntity_.JOURNEYS, JoinType.LEFT);
        final Predicate byJourneyName = criteriaBuilder.equal(journeys.get(JourneyEntity_.STATION_FROM), criteriaBuilder.parameter(String.class, "stationFromParam"));
        journeys.on(byJourneyName);
        final Predicate byName = criteriaBuilder.equal(from.get(VehicleEntity_.NAME), criteriaBuilder.parameter(String.class, "nameParam"));
        final Predicate active = criteriaBuilder.equal(from.get(VehicleEntity_.ACTIVE), criteriaBuilder.parameter(Boolean.class, "activeParam"));
        return entityManager.createQuery(query
                .select(from)  // это select from нашей entity, где (следующая строчка):
                //.where(byName, active, byJourneyName))
                .where(byName, active).orderBy(new OrderImpl(from.get(VehicleEntity_.ID), false))
        )
                .setParameter("nameParam", name)
                .setParameter("activeParam", true)
                .setParameter("stationFromParam", "from 1")
                .setFirstResult(3)
                .setMaxResults(3)
                .getResultList();*/

        // сортировка на hql
      /*  return entityManager
                .createQuery("select v  from VehicleEntity v left join v.journeys js on js.vehicle.id = v.id order by v.id desc " , VehicleEntity.class) // sort by id
                //.createQuery("select v  from VehicleEntity v left join v.journeys js on js.vehicle.id = v.id" , VehicleEntity.class)
                .setMaxResults(3)
                .setFirstResult(1)
                .getResultList();*/

        return entityManager
                //.createQuery("select v  from VehicleEntity v left join v.journeys js on js.vehicle.id = v.id order by v.id desc " , VehicleEntity.class) // sort by id
                .createQuery("select v  from VehicleEntity v left join v." + VehicleEntity_.JOURNEYS + " js on js.vehicle.id = v.id order by v.id desc " , VehicleEntity.class) // sort by id
                //.createQuery("select v  from VehicleEntity v left join v.journeys js on js.vehicle.id = v.id" , VehicleEntity.class)
                .setMaxResults(3)
                .setFirstResult(1)
                .getResultList();
    }


}
