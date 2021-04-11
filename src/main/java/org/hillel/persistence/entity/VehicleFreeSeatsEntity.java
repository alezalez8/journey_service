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
public class VehicleFreeSeatsEntity {

    @Id
    @GeneratedValue()
    private Long id;


    @Column(name = "seats")
    private int seats;

    @Column(name = "free_seats")
    private int freeSeats;


    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private VehicleEntity vehicleEntity ;


}
