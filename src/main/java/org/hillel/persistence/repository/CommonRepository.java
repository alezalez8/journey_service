package org.hillel.persistence.repository;


import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hillel.persistence.entity.AbstractModifyEntity;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public abstract class CommonRepository<E extends AbstractModifyEntity<ID>, ID extends Serializable> implements GenericRepository<E, ID> {

    @PersistenceContext
    protected EntityManager entityManager;
    private final Class<E> entityClass; // for different entity (stop, vehicle etc.)

    protected CommonRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public E createOrUpdate(E entity) {
        Assert.notNull(entity, "entity must be set");
        if (Objects.isNull(entity.getId())) {
            entityManager.persist(entity);
        } else {
            return entityManager.merge(entity);
        }
        return entity;
    }

    @Override
    public Optional<E> findById(ID id) {
        if (Objects.isNull(id)) return Optional.empty();
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @SneakyThrows
    @Override
    public void removeById(ID id) {
        final E reference = entityManager.getReference(entityClass, id); // return only current object, without link wit other objects!
        entityManager.remove(reference);
    }

    @Override
    public void remove(E entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            removeById(entity.getId());
        }
    }

    @Override
    public Collection<E> findByIds(ID... ids) {
        return entityManager.unwrap(Session.class).byMultipleIds(entityClass).multiLoad(ids);
    }

    //@Override
    public Collection<E> findAll() {
//        return entityManager.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList(); // first example hql-querry
//        return entityManager.createNativeQuery("s
//        elect  from " + entityClass.getAnnotation(Table.class).name(), entityClass).getResultList(); // second example

        // критерий запроса ===============================
        /*final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass); // query - заготовка для дальнейшего sql запроса
        final Root<E> from = query.from(entityClass); // с рута(from) строим все зависимости, т.е. в "select * from journey" journey - это и есть рут
        return entityManager.createQuery(query.select(from)).getResultList();*/
        // критерий запроса конец ===============================

        // заготова для формирования вызова хранимых функций
        return entityManager.createStoredProcedureQuery("find_all", entityClass).
                registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR).    // на 1-ой позиции мы говорим, что возвращается REF_CURSOR
                registerStoredProcedureParameter(2, String.class, ParameterMode.IN).         // а входной параметр - это строка
                setParameter(2, entityClass.getAnnotation(Table.class).name()).
                getResultList();

    }
}
