package org.hillel.service;

import org.hibernate.cfg.Environment;
import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;


@Component
public class  TicketClient {

    /*private JourneyService journeyService;

    public TicketClient() {
    }

    @Autowired
    @Qualifier("inMemoryJourneyService")
    public void setJourneyService(JourneyService journeyService) {
        this.journeyService = journeyService;
    }


    public TicketClient(@Qualifier("inMemoryJourneyService") JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        // TODO check input params

        return journeyService.find(stationFrom, stationTo, dateFrom, dateTo);
    }*/


    //===================================== latest ticketClient



     @Autowired
    private Map<String, JourneyService> journeyService;

    @Autowired
    private TransactionalJourneyService transactionalJourneyService;

    private Environment environment;
    public Long createJourney(final JourneyEntity journeyEntity) {
        return transactionalJourneyService.createJourney(journeyEntity);
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        // TODO check input params

        return journeyService.get("InMemoryJourneyService").find(stationFrom, stationTo, dateFrom, dateTo);
    }

    public static void main(String[] args) {
       // final InDBJourneyServiceImpl journeyService = new InDBJourneyServiceImpl(); delete db
       // final TicketClientDB client = new TicketClientDB(journeyService);           delete db
       // System.out.println(client.find("Odessa", "Lviv", LocalDate.now().plusDays(1), LocalDate.now().plusDays(3)));


    }


}
