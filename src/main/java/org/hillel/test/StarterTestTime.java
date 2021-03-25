package org.hillel.test;

import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.time.LocalDate;

public class StarterTestTime {
    public static void main(String[] args) {

        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < 150; i++) {
            JourneyEntity journeyEntity = new JourneyEntity();
            journeyEntity.setStationFrom("Odessa");
            journeyEntity.setStationTo("Antalia");
            journeyEntity.setDeparture(LocalDate.now());
            journeyEntity.setArrival(LocalDate.now().plusDays(i));
            ticketClient.createJourney(journeyEntity);
            // System.out.println("create journey with id =  " + ticketClient.createJourney(journeyEntity));

        }
        System.out.println("BD is created");
        System.out.println("Time all transaction is:  " + (System.currentTimeMillis() - timeStart) + " ms");
    }
}
