package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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
public class VehicleEntity extends AbstractModifyEntity<Long> {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "vehicle")  // , cascade = {CascadeType.PERSIST, CascadeType.MERGE}
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
}
