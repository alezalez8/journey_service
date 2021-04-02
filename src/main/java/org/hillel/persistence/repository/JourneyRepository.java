package org.hillel.persistence.repository;


import org.hillel.persistence.entity.JourneyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class JourneyRepository  {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(final JourneyEntity journeyEntity) {
        if(journeyEntity == null) {
            throw new IllegalArgumentException("Unable to create new record");
        }
        entityManager.persist(journeyEntity);
        return journeyEntity.getId();

    }

    public Optional<JourneyEntity> findById(Long id) {
      return Optional.ofNullable(entityManager.find(JourneyEntity.class, id));
    }
}
