package org.hillel.persistence.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "schedule_service")
public class JourneyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "station_from", length = 50, nullable = false)
    private String stationFrom;

    @Column(name = "station_to", length = 50, nullable = false)
    private String stationTo;


    @Column(name = "departure", length = 10, nullable = false)
    private LocalDate departure;

    @Column(name = "arrival", length = 10, nullable = false)
    private LocalDate arrival;

}
