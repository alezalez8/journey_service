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

    //@Autowired
    private List<JourneyService> journeyServices;

    @Autowired
    private TransactionalJourneyService journeyService;

    @Autowired
    private TransactionalStopService stopService;

    private Environment environment;

    @Value("${datasource.url}")
    private String url;

    public TicketClient() {
    }

    public Long createJourney(final JourneyEntity journeyEntity) {
        return journeyService.createJourney(journeyEntity);
    }

    public Optional<JourneyEntity> getJourneyById(Long id) {
        //Assert.notNull(id, "id must be set");
        return id == null ? Optional.empty() : journeyService.getById(id);
    }


    public Long createStop(final StopEntity stopEntity) {
        return stopService.createStop(stopEntity);
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) throws Exception {
        if (!StringUtils.hasText(stationFrom))
            throw new IllegalArgumentException("stationFrom must be set");
        if (!StringUtils.hasText(stationTo))
            throw new IllegalArgumentException("stationTo must be set");
        if (dateFrom == null)
            throw new IllegalArgumentException("dateFrom must be set");
        if (dateTo == null)
            throw new IllegalArgumentException("dateTo must be set");

//        List<Journey> rez = new ArrayList<>();
//        return journeyServices.get("dbJourneyService").find(stationFrom, stationTo, dateFrom, dateTo);
        for (JourneyService service : journeyServices) {
            System.out.println(service.getClass().getName());
//            rez.addAll(service.find(stationFrom, stationTo, dateFrom, dateTo));
        }
//        return rez;
        return Collections.emptyList();
    }

    @PostConstruct
    public void doPost() {
        System.out.println("post construct");
//        System.out.println(url);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy");
    }

    /*@Autowired
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


*//*
    public Long createJourney(final JourneyEntity journeyEntity) {
        return transactionalJourneyService.createJourney(journeyEntity);
    }
*//*

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
    }*/


}

