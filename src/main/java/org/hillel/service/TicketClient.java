package org.hillel.service;

import org.hibernate.cfg.Environment;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;


@Component
public class TicketClient {


    public TicketClient() {
    }

    @Autowired
    private TransactionalJourneyService transactionalJourneyService;

    @Autowired
    private TransactionalStopService stopService;

    private Environment environment;

    public Long createJourney(final JourneyEntity journeyEntity) {
        return transactionalJourneyService.createJourney(journeyEntity);
    }
    public Long createStop(final StopEntity stopEntity) {
        return stopService.createStop(stopEntity);
    }

    /*public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) throws Exception {
        if (!StringUtils.hasText(stationFrom))
            throw new IllegalArgumentException("stationFrom must be set");
        if (!StringUtils.hasText(stationTo))
            throw new IllegalArgumentException("stationTo must be set");
        if (dateFrom == null)
            throw new IllegalArgumentException("dateFrom must be set");
        if (dateTo == null)
            throw new IllegalArgumentException("dateTo must be set");


        for (JourneyService service : journeyServices) {
            System.out.println(service.getClass().getName());
        }

        return Collections.emptyList();
    }*/


}

