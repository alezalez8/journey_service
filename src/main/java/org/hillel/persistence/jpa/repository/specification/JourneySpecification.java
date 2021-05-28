package org.hillel.persistence.jpa.repository.specification;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.JourneyEntity_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public interface JourneySpecification {

    static Specification<JourneyEntity> allJorneytoNeedRout(final String from, final String to) {
        return (root, query, criteriaBuilder) -> {
            final Predicate stationFrom = criteriaBuilder.equal(root.get(JourneyEntity_.STATION_FROM), criteriaBuilder.literal(from));
            final Predicate stationTo = criteriaBuilder.equal(root.get(JourneyEntity_.STATION_TO), criteriaBuilder.literal(to));
            return criteriaBuilder.and(stationFrom, stationTo);
        };
    }
}
