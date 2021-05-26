package org.hillel.persistence.jpa.repository;

import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CommonJpaRepository<E extends Persistable<ID>, ID extends Serializable> extends JpaRepository<E, Long> {



}


