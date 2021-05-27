package org.hillel.persistence.jpa.repository.specification;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleEntity_;
import org.springframework.data.jpa.domain.Specification;

import java.util.HashMap;
import java.util.Map;

public enum VehicleSpecificationByName {
    VEH_NAME(), VEH_ID(), VEH_ALL();
    private static String byName;
    private static Long byId;




    Map<String, VehicleSpecificationByName> queryMap = new HashMap<>();

    VehicleSpecificationByName() {

    }


    static Specification<VehicleEntity> byName(final String name) {
        return (root, query, criteriaBuilder) ->   // можно через квери делать сложные запросы, типа query.where, query.orderBy etc.
                criteriaBuilder.equal(root.get(VehicleEntity_.NAME), criteriaBuilder.literal(name));
    }

}
/*
* static Specification<VehicleEntity> byName(final String name) {
        return (root, query, criteriaBuilder) ->   // можно через квери делать сложные запросы, типа query.where, query.orderBy etc.
                criteriaBuilder.equal(root.get(VehicleEntity_.NAME), criteriaBuilder.literal(name));

    }
// совмещение Specification  и Example, не очень хороший подход
    static Specification<VehicleEntity> byNameAndExample(final String name, final VehicleEntity vehicle) {
        return (root, query, criteriaBuilder) -> {
            final Predicate byName = criteriaBuilder.equal(root.get(VehicleEntity_.NAME), criteriaBuilder.literal(name));
            final Predicate byExample = QueryByExamplePredicateBuilder.getPredicate(root, criteriaBuilder, Example.of(vehicle));
            return criteriaBuilder.and(byName, byExample);
        };
    }

        static Specification<VehicleEntity> onlyActive () {
            return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get(VehicleEntity_.ACTIVE));

        }
* */