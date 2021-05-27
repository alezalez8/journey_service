package org.hillel.persistence.jpa.repository;

//import org.hillel.persistence.entity.AbstractModifyEntity;
//import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
//import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@NoRepositoryBean
public interface CommonJpaRepository<E extends Persistable<ID>, ID extends Serializable> extends JpaRepository<E, Long> {

    @Query("select e from #{#entityName} e where e.active = true")
    List<E> findOnlyActive();

    @Query("select e from #{#entityName} e")
    List<E> findAll();


   @Query("select e from #{#entityName} e where e.name = :name")
   List<E> findByName(String name);

    @Query("select e from #{#entityName} e where e.id = :id")
    void findById(Long id);


    // @Transactional
    @Modifying
    @Query("update  #{#entityName} e set e.active = false  where e.id = :id")
    void disableById(@Param("id") ID id);
}
