package org.hillel.persistence.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "vehicle_seat")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
public class VehicleFreeSeatsEntity  extends AbstractModifyEntity<Long> {



    @Column(name = "free_seats")
    private int freeSeats;


    /*@OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private VehicleEntity vehicleEntity ;*/

    @ManyToOne
    @MapsId
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicleEntity ;


    @ManyToOne
    @JoinColumn(name = "journey_id", nullable = false)
    private JourneyEntity journey;


}
