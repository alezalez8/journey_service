package org.hillel.persistence.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "vehicle_seat")
@Getter
@Setter
@NoArgsConstructor
public class VehicleFreeSeatsEntity  extends AbstractModifyEntity<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@Column(name = "seats")
    private int seats;*/

    @Column(name = "free_seats")
    private Integer freeSeats;


    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private VehicleEntity vehicleEntity ;

    @ManyToOne
    @JoinColumn(name = "journey_id", nullable = false)
    private JourneyEntity journey;


}
