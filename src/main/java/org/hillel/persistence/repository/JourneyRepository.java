package org.hillel.persistence.repository;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Objects;

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

    public EntityManager getEntityManager() {
        return entityManager;
    }


    @Override
    public Collection<JourneyEntity> findAllAsNamed() {
        return entityManager.createNamedQuery("findJourneyAll", JourneyEntity.class).getResultList();
    }
}