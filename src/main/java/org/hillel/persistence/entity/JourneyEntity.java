package org.hillel.persistence.entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "schedule_service")
public class JourneyEntity {

    @Id
   // @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public JourneyEntity() {
    }

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

    public String getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(String stationFrom) {
        this.stationFrom = stationFrom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationTo() {
        return stationTo;
    }

    public void setStationTo(String stationTo) {
        this.stationTo = stationTo;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }
}
