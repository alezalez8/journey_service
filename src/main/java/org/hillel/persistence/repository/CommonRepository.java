package org.hillel.persistence.repository;


import lombok.SneakyThrows;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.hillel.persistence.entity.AbstractModifyEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.service.SearchQueryParam;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
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
        return entityManager.createNativeQuery("select * from " + entityClass.getAnnotation(Table.class).name(), entityClass)
                .getResultList();
    }

    @Override
    public Collection<E> findAllAsCriteria() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);
        final Root<E> from = query.from(entityClass);
        return entityManager.createQuery(query.select(from)).getResultList();
    }


    @Override
    public Collection<E> findAllAsStoredProcedure() {
        return entityManager.createStoredProcedureQuery("find_all", entityClass).   // "find_all" - это со стороны базы данных
                registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR).    // на 1-ой позиции мы говорим, что возвращается REF_CURSOR
                registerStoredProcedureParameter(2, String.class, ParameterMode.IN).         // а входной параметр - это строка
                setParameter(2, entityClass.getAnnotation(Table.class).name()).              // передаем имя таблицы
                getResultList();
    }

    @SneakyThrows
    @Override
    public void removeById(ID id) {
        final E reference = entityManager.getReference(entityClass, id); // return only current object, without link wit other objects!
        entityManager.remove(reference);
    }


    // критериабилдер, вызов с параметрами и  сортировкой
    public Collection<E> findAllAsCriteriaBuildWithParams(SearchQueryParam searchQueryParam) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);
        Root<E> from = query.from(entityClass);
        System.out.println("params is " + searchQueryParam.getQueryParam() + ", asc = " + searchQueryParam.isAscSort());
        Order order = new OrderImpl(from.get(searchQueryParam.getQueryParam()));
        return entityManager.createQuery(query.select(from).orderBy(order))
                .setFirstResult(searchQueryParam.getPageIndex())
                .setMaxResults(searchQueryParam.getMaxResult())
                .getResultList();

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