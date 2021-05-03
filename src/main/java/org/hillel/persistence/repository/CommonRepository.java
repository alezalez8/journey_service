package org.hillel.persistence.repository;


import lombok.SneakyThrows;
import org.hillel.persistence.entity.AbstractModifyEntity;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public abstract class CommonRepository<E extends AbstractModifyEntity<ID>, ID extends Serializable>
        implements GenericRepository<E, ID> {

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


    @Override
    public Collection<E> findAll() {
        return entityManager.createQuery("from " + entityClass.getSimpleName(), entityClass)
                .getResultList();
    }


    @Override
    public Collection<E> findAllAsNative() {
        return entityManager.createNativeQuery("select * from " + entityClass.getAnnotation(Table.class).name()).getResultList();
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

}