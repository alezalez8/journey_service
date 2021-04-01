package org.hillel.service;

import org.hibernate.cfg.Environment;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Component
public class TicketClient {

    @Autowired
    private List<JourneyService> journeyServices;

    @Autowired
    private TransactionalJourneyService journeyService;

    @Autowired
    private JourneyService journeyService;

    @Autowired
    private TransactionalStopService stopService;

    @Autowired
    private Environment environment;

    @Autowired
    private TransactionalJourneyService transactionalJourneyService;


    @Value("${datasource.url}")
    private String url;


    public TicketClient() {
    }


/*
    public Long createJourney(final JourneyEntity journeyEntity) {
        return transactionalJourneyService.createJourney(journeyEntity);
    }
*/

    public Long createJourney(final JourneyEntity journeyEntity) {
        return journeyService.createJourney(journeyEntity);
    }


    public Optional<JourneyEntity> getJourneyById(Long id) {
        //Assert.notNull(id, "id must be set");
        return id == null ? Optional.empty() : journeyService.    }


    public Long createStop(final StopEntity stopEntity) {
        return stopService.createStop(stopEntity);
    }

    @PostConstruct
    public void doPost() {
        System.out.println("post construct");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy");
    }


}

