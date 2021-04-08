package org.hillel.persistence.repository;


import org.hibernate.Session;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JourneyRepository extends CommonRepository<JourneyEntity, Long> {

    protected JourneyRepository() {
        super(JourneyEntity.class);
    }

    @Override
    public JourneyEntity createOrUpdate(JourneyEntity entity) {
        VehicleEntity vehicleEntity = entity.getVehicle();
        if (Objects.nonNull(entity.getVehicle())) {
            if (!entityManager.contains(vehicleEntity)) {
                entity.setVehicle(entityManager.merge(vehicleEntity));
            }
        }
       return super.createOrUpdate(entity);
    }




}
