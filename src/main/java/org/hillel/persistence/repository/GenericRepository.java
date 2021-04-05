package org.hillel.persistence.repository;

import java.util.Optional;

public interface GenericRepository<E, ID> {

    E createOrUpdate(E entity);

    Optional<E> findById(ID id);

    void removeId(ID id);

    void remove(E entity);
}
