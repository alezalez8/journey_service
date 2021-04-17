package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor

@org.hibernate.annotations.NamedQueries(value = {
        @org.hibernate.annotations.NamedQuery(name = "findAll", query = "from VehicleEntity")
})

@NamedStoredProcedureQueries(
        @NamedStoredProcedureQuery(
                name = "findAllVehicle",
                procedureName = "find_all_vehicles",
                parameters = @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = Class.class),
                resultClasses = VehicleEntity.class
        )
)
public class VehicleEntity extends AbstractModifyEntity<Long> {

    @Column(name = "name")
    private String name;

    //    @OneToMany(mappedBy = "vehicle", orphanRemoval = true )  // , cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    @OneToMany(mappedBy = "vehicle", cascade = {CascadeType.REMOVE})
    private Set<JourneyEntity> journeys = new HashSet<>();

    public void addJourney(final JourneyEntity journeyEntity) {
        // todo
        if (journeys == null) {
            journeys = new HashSet<>();
        }
        journeys.add(journeyEntity);
        journeyEntity.addVehicle(this);
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "name='" + name + '\'' + '}';

    }

/*
    public void removeAllJourney() {
        if (CollectionUtils.isEmpty(journeys)) return;
        journeys.forEach(item -> item.setVehicle(null));
    }
*/
}
