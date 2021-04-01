package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
public class VehicleEntity extends AbstractModifyEntity<Long> {

    /*@Embedded
    private CommonInfo commonInfo;*/

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "vehicle")
    private List<JourneyEntity> journeys = new ArrayList<>();

    public void addJourney(final JourneyEntity journeyEntity) {
        if (journeys == null) {
            journeys = new ArrayList<>();
        }
        journeys.add(journeyEntity);
        journeyEntity.addVehicle(this);
    }
}
