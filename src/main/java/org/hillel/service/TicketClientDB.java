package org.hillel.service;

import org.hillel.Journey;
import org.hillel.dao.DataConnect;

import java.time.LocalDate;
import java.util.*;

public class TicketClientDB implements JourneyService {
    // there should be DB
    //private Map<String, List<Journey>> storage = new HashMap<>();
    //private Map<String, List<Journey>> storage = DataConnect.getBD();






    private List<Journey> createJourney(String from, String to) {
        return Arrays.asList(
                new Journey(from, to, LocalDate.now(), LocalDate.now().plusDays(1)),
                new Journey(from, to, LocalDate.now().plusDays(1), LocalDate.now().plusDays(2)),
                new Journey(from, to, LocalDate.now().plusDays(2), LocalDate.now().plusDays(3))
        );
    }


    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {

       // return Collections.unmodifiableList(out);
        return null;
    }

}

