package org.hillel.persistence.jpa.repository;

import org.hillel.persistence.entity.AbstractModifyEntity;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public interface CommonJpaRepository<E extends Persistable<ID>, ID extends Serializable> {

    @Query("select e from #{#entityName} e where e.active = true")
    List<E> findOnlyActive();

//    List<E> findByName(String name);

   // @Transactional
    @Modifying
    @Query("update  #{#entityName} e set e.active = false  where e.id = :id")
    void disableById(@Param("id") ID id);
}
