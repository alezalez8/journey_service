package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.context.AppContext;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.enums.DirectionType;
import org.hillel.service.TicketClient;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.hillel.service.JourneyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Starter {
    public static void main(String[] args) {
        final Date date = new Date();
        Calendar calendar = new GregorianCalendar();


        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom("Odessa");
        journeyEntity.setStationTo("Antalia");
        journeyEntity.setDeparture(date);
        journeyEntity.setDirection(DirectionType.UNKNOW);
        calendar.clear(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        journeyEntity.setArrival(calendar.getTime());
        System.out.println("create journey with id =  " + ticketClient.createJourney(journeyEntity));

        JourneyEntity journeyEntity1 = new JourneyEntity();
        journeyEntity1.setStationFrom("Odessa");
        journeyEntity1.setStationTo("Kimer");
        journeyEntity1.setDeparture(date);
        calendar.clear(Calendar.DAY_OF_YEAR);
        calendar.getTime();
        calendar.set(Calendar.DAY_OF_YEAR, 2);
        journeyEntity1.setArrival(calendar.getTime());
        System.out.println("create journey with id =  " + ticketClient.createJourney(journeyEntity1));

        JourneyEntity journeyEntity2 = new JourneyEntity();
        journeyEntity2.setStationFrom("Kiev");
        journeyEntity2.setStationTo("Lviv");
        journeyEntity2.setDeparture(date);
        calendar.clear(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, 3);
        journeyEntity2.setArrival(calendar.getTime());
        System.out.println("create journey with id =  " + ticketClient.createJourney(journeyEntity2));


    }
}
