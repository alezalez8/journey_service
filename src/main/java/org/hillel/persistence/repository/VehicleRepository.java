package org.hillel.persistence.repository;

import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Objects;

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
}
