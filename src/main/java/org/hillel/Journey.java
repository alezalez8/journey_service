package org.hillel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Journey  {
    private final String stationFrom;
    private final String stationTo;
    private final LocalDate departure;
    private final LocalDate arrival;
    private final String route;

    public Journey(final String stationFrom, final String stationTo, final LocalDate departure,
                   final LocalDate arrival) {
        if (stationFrom == null || stationTo == null)
            throw new IllegalArgumentException("station from and to must be a set");
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.departure = departure;
        this.arrival = arrival;
        this.route = stationFrom + "->" + stationTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Journey journey = (Journey) o;

        if (stationFrom != null ? !stationFrom.equals(journey.stationFrom) : journey.stationFrom != null) return false;
        if (stationTo != null ? !stationTo.equals(journey.stationTo) : journey.stationTo != null) return false;
        if (departure != null ? !departure.equals(journey.departure) : journey.departure != null) return false;
        if (arrival != null ? !arrival.equals(journey.arrival) : journey.arrival != null) return false;
        return route != null ? route.equals(journey.route) : journey.route == null;
    }

    @Override
    public int hashCode() {
        int result = stationFrom != null ? stationFrom.hashCode() : 0;
        result = 31 * result + (stationTo != null ? stationTo.hashCode() : 0);
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (arrival != null ? arrival.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        return result;
    }

    public String getStationFrom() {
        return stationFrom;
    }

    public String getStationTo() {
        return stationTo;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public String getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "stationFrom='" + stationFrom + '\'' +
                ", stationTo='" + stationTo + '\'' +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", route='" + route + '\'' +
                '}';
    }
}
