package org.hillel.persistence.repository;

import org.hillel.persistence.entity.VehicleEntity;

import java.util.Collection;
import java.util.Optional;

public interface GenericRepository<E, ID> {

    E createOrUpdate(E entity);

    Optional<E> findById(ID id);

    void removeById(ID id);

    void remove(E entity);


    // add methods for hw5:

    /*
    * 1. findAll (метод поиска через HQL)
2. findAllAsNative (метод поиска через вызов sql запроса)
3. findAllAsNamed (метод поиска через вызов именованного запроса по алиасу)
4. findAllAsCriteria (метод поиска через CriteriaBuilder)
5. findAllAsStoredProcedure (метод поиска через вызов хранимой функции)

То есть для каждой сущности должен быть свой repository и service классы,
в рамках которых вы реализуете все требуемые методы.
    * */

    Collection<E> findAll();

    Collection<E> findAllAsNative();

    Collection<E> findAllAsCriteria();

    Collection<E> findAllAsNamed();


}