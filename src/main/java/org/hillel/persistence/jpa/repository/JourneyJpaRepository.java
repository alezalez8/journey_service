package org.hillel.persistence.jpa.repository;

import com.sun.xml.bind.v2.model.core.ID;
import org.hillel.persistence.entity.JourneyEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface JourneyJpaRepository extends CommonJpaRepository<JourneyEntity, Long>,
        JpaSpecificationExecutor<JourneyEntity> {
}
