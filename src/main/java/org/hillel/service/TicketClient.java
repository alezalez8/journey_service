package org.hillel.service;

import org.hillel.Journey;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

public class TicketClient {
    private JourneyService journeyService;

    public TicketClient(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        // TODO check input params
        return journeyService.find(stationFrom, stationTo, dateFrom, dateTo);
    }

    public static void main(String[] args) {
        final JourneyService journeyService = new InMemoryJourneyServiceImpl();
        TicketClient client = new TicketClient(journeyService);
        System.out.println(client.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));


    }

}

