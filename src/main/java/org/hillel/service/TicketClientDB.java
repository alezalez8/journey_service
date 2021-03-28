package org.hillel.service;

import org.hillel.Journey;
import org.hillel.dao.DataConnect;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class TicketClientDB {
    private JourneyService journeyService;

    public TicketClientDB(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) throws SQLException {
        // TODO check input params

        return journeyService.find(stationFrom, stationTo, dateFrom, dateTo);
    }

    public static void main(String[] args) throws SQLException {
        final InDBJourneyServiceImpl journeyService = new InDBJourneyServiceImpl();
        final TicketClientDB client = new TicketClientDB(journeyService);
        System.out.println(client.find("Odessa", "Lviv", LocalDate.now().plusDays(1), LocalDate.now().plusDays(1)));


    }

}

