package org.hillel.persistence.repository;


import org.hillel.persistence.entity.AbstractModifyEntity;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public abstract class CommonRepository<E extends AbstractModifyEntity<ID>, ID extends Serializable> implements GenericRepository<E, ID> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public E createOrUpdate(E entity) {
        Assert.notNull(entity, "entity must be set");
        if (Objects.isNull(entity.getId())){
            entityManager.persist(entity);
        } else {
            return entityManager.merge(entity);
        }

            return null;
    }

    @Override
    public Optional<E> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public void removeId(ID id) {

    }

    @Override
    public void remove(E entity) {

    }
}
