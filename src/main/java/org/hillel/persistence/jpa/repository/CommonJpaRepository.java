package org.hillel.persistence.jpa.repository;

import org.hillel.persistence.entity.AbstractModifyEntity;

import java.io.Serializable;

public interface CommonJpaRepository<E extends AbstractModifyEntity<D>, ID extends Serializable> {
}
