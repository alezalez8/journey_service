package org.hillel.persistence.repository;


import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hillel.persistence.entity.AbstractModifyEntity;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.criteria.*;
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

    @Override
    public Collection<E> findByName(String name) {
        // это было в уроке 7
        // используем заготовку CriteriaBuilder:
/*        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);
        final Root<E> from = query.from(entityClass);
        final Join<Object, Object> journeys = from.join("journeys", JoinType.LEFT);
        final Predicate byName = criteriaBuilder.equal(from.get("name"), criteriaBuilder.literal(name));
        final Predicate active = criteriaBuilder.equal(from.get("active"), criteriaBuilder.literal(true));
        final Predicate byJourneyName = criteriaBuilder.equal(journeys.get("stationFrom"), criteriaBuilder.literal("from 1"));
        return entityManager.createQuery(query.
                select(from).  // это select from нашей entity, где (следующая строчка):
                where(byName, active, byJourneyName)).    // идем в сущность VehicleEntity и запросим поле name и его сравним с теми данными,
                getResultList(); */                       // которые есть в БД. from.get("name") - name принадлежит from'у

        // ==========================  параметризация по индексу ================================
/*
        return entityManager.createNativeQuery("select e.* from " + entityClass.getAnnotation(Table.class).name() + " e " + " where e.name = ?", entityClass)
                .setParameter(1, name)
                .getResultList();  // параметризация по нэйтиву sql*/
/*
        return entityManager.createQuery("from " + entityClass.getName() + " e where e.name = " + "?1", entityClass).  // без индекса 1 после ? работать не будет
                setParameter(1, name).getResultList();   // параметризация по hql. если несколько пар-ров, то пишем "?1 ?2 ?3"

*/
        // ==========================  параметризация по названию ================================
        /*return entityManager.createQuery("from " + entityClass.getName() + " e where e.name = :entityName and e.active = :activeParam", entityClass)  // без индекса 1 после ? работать не будет
                .setParameter("entityName", name)
                .setParameter("activeParam", true)
                .getResultList();*/

        // ==========================  параметризация по названию, нативный запрос====================
        /*return entityManager.createNativeQuery("select e.* from " + entityClass.getAnnotation(Table.class).name() + " e " +
                " where e.name = :entityName and e.active = :activeParam", entityClass)
                .setParameter("entityName", name)
                .setParameter("activeParam", "yes")  // в случае нэйтив запроса булево значение нужно использовать в виде строки, т.к. у нас
                .getResultList();                           // конвертер в AbstractModifyEntity*/


        // ==========================  вариант с предикатами   ====================
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<E> query = criteriaBuilder.createQuery(entityClass);
        final Root<E> from = query.from(entityClass);
        final Join<Object, Object> journeys = from.join("journeys", JoinType.LEFT);  // здесь "journeys" - имя поля в сущности
        final Predicate byJourneyName = criteriaBuilder.equal(journeys.get("stationFrom"), criteriaBuilder.parameter(String.class, "stationFromParam"));
        journeys.on(byJourneyName);
        final Predicate byName = criteriaBuilder.equal(from.get(" name"), criteriaBuilder.parameter(String.class, "nameParam"));
        final Predicate active = criteriaBuilder.equal(from.get("active"), criteriaBuilder.parameter(Boolean.class, "activeParam"));
        return entityManager.createQuery(query
                .select(from)  // это select from нашей entity, где (следующая строчка):
                //.where(byName, active, byJourneyName))
                .where(byName, active))
                .setParameter("nameParam", name)
                .setParameter("activeParam", true)
                .setParameter("stationFromParam", "from 1")
                .getResultList();
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
        // обобщенная хранимая процедура на стороне БД
        return entityManager.createStoredProcedureQuery("find_all", entityClass).
                registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR).    // на 1-ой позиции мы говорим, что возвращается REF_CURSOR
                registerStoredProcedureParameter(2, String.class, ParameterMode.IN).         // а входной параметр - это строка
                setParameter(2, entityClass.getAnnotation(Table.class).name()).
                getResultList();

    }
}
