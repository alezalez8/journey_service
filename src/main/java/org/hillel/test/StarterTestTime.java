package org.hillel.test;

import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class StarterTestTime {
    public static void main(String[] args) {

        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        long timeStart = System.currentTimeMillis();
        Calendar calendar = new GregorianCalendar();
        Date date;


        date = calendar.getTime();
        for (int i = 0; i < 150; i++) {
            JourneyEntity journeyEntity = new JourneyEntity();
            journeyEntity.setStationFrom("Odessa");
            journeyEntity.setStationTo("Antalia");

//            journeyEntity.setDeparture(Instant.now());
            journeyEntity.setDeparture(date);
            calendar.clear(Calendar.DAY_OF_YEAR);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            journeyEntity.setArrival(calendar.getTime());

            ticketClient.createJourney(journeyEntity);

        }
        System.out.println("BD is created");
        System.out.println("Time all transaction is:  " + (System.currentTimeMillis() - timeStart) + " ms");
    }
}
