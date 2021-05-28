package org.hillel.persistence.jpa.repository.specification;

import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.StopEntity_;
import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public interface StopSpecification {

    static Specification<VehicleEntity> byCreateDate(Date createDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(StopEntity_.CREATE_DATE), criteriaBuilder.literal(createDate));
    }

    static Specification<StopEntity> onlyActive() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(StopEntity_.ACTIVE));
    }


}
