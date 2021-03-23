package org.hillel.persistence.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "schedule_service")
@Getter
@Setter
@NoArgsConstructor
public class JourneyEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public JourneyEntity(String stationFrom, String stationTo, LocalDate departure, LocalDate arrival) {
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.departure = departure;
        this.arrival = arrival;
    }

    @Column(name = "station_from", length = 50, nullable = false)
    private String stationFrom;

    @Column(name = "station_to", length = 50, nullable = false)
    private String stationTo;


    @Column(name = "departure", length = 10, nullable = false)
    private LocalDate departure;

    @Column(name = "arrival", length = 10, nullable = false)
    private LocalDate arrival;


}
