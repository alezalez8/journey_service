package org.hillel.persistence.repository;


import org.hibernate.Session;
import org.hillel.persistence.entity.JourneyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class JourneyRepository extends CommonRepository<JourneyEntity, Long> {

    protected JourneyRepository() {
        super(JourneyEntity.class);
    }

}
