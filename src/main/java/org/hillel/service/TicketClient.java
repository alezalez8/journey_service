package org.hillel.service;

import org.hibernate.cfg.Environment;
import org.hillel.persistence.entity.JourneyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;


@Component
public class TicketClient {


    public TicketClient() {
    }

    @Autowired
    private TransactionalJourneyService transactionalJourneyService;

    private Environment environment;

    public Long createJourney(final JourneyEntity journeyEntity) throws IOException {
        return transactionalJourneyService.createJourney(journeyEntity);
    }


}

