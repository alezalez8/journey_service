package org.hillel.persistence.jpa.repository.specification;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleEntity_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public interface VehicleSpecification {
    static Specification<VehicleEntity> byName(final String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(VehicleEntity_.NAME), criteriaBuilder.literal(name));
    }

    static Specification<VehicleEntity> onlyActive() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(VehicleEntity_.ACTIVE));
    }

    static Specification<VehicleEntity> byNameAndActive(final String name) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            final Predicate byName = criteriaBuilder.equal(root.get(VehicleEntity_.NAME), criteriaBuilder.literal(name));
            final Predicate onlyActive = criteriaBuilder.isTrue(root.get(VehicleEntity_.ACTIVE));
            return criteriaBuilder.and(byName, onlyActive);
        });
    }

}
