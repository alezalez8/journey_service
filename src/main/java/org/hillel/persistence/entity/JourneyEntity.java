package org.hillel.persistence.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hillel.persistence.entity.enums.DirectionType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "schedule_service")
@Getter
@Setter
@NoArgsConstructor
public class JourneyEntity extends AbstractModifyEntity<Long> implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JourneyEntity)) return false;
        JourneyEntity entity = (JourneyEntity) o;
        return getId() != null && Objects.equals(getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

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

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    public void addVehicle(final VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "JourneyEntity{" +
                "stationFrom='" + stationFrom + '\'' +
                ", stationTo='" + stationTo + '\'' +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", direction=" + direction +
                ", vehicle=" + vehicle +
                '}';
    }
}
