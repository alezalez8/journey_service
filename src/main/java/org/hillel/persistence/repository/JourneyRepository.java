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


//======================= end of lesson # 5  ===========================================

   /*

    @PersistenceContext
    private EntityManager entityManager;

public Long create(final JourneyEntity journeyEntity) {
        if (journeyEntity == null) {
            throw new IllegalArgumentException("Unable to create new record");
        }
        entityManager.persist(journeyEntity);
        return journeyEntity.getId();

    }
//    ========== uses var 1


    *//*public Optional<JourneyEntity> findById(Long id) {
      return Optional.ofNullable(entityManager.find(JourneyEntity.class, id));
    }*//*


    //    ========== uses var 2
    public Optional<JourneyEntity> findById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        final JourneyEntity value = entityManager.find(JourneyEntity.class, id);
        //final JourneyEntity value = session.byMultipleIds(JourneyEntity.class).multiLoad(id).get(0); // get value by id

        return Optional.ofNullable(value);
    }


    public JourneyEntity save(JourneyEntity journey) {
//        entityManager.merge(journey);
        final JourneyEntity merge = entityManager.merge(journey);
        entityManager.flush();
        return merge;
    }*/
}
