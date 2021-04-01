package org.hillel.persistence.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hillel.persistence.entity.enums.DirectionType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "schedule_service")
@Getter
@Setter
@NoArgsConstructor
public class JourneyEntity extends AbstractModifyEntity<Long> implements Serializable {

    @Column(name = "station_from", length = 80, nullable = false)
    private String stationFrom;

    @Column(name = "station_to", length = 80, nullable = false)
    private String stationTo;


    @Column(name = "departure", nullable = false)
    @Temporal(TemporalType.DATE)
    //private Instant departure;
    private Date departure;

    @Column(name = "arrival", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date arrival;

    @Column(name = "direction", length = 20)
    @Enumerated(EnumType.STRING)
    private DirectionType direction = DirectionType.TO;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    public void addVehicle(final VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }
}
