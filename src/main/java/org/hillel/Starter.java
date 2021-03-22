package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.context.AppContext;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.service.TicketClient;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.hillel.service.JourneyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.time.LocalDate;

public class Starter {
    public static void main(String[] args)  {

        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom("Odessa");
        journeyEntity.setStationTo("Antalia");
        journeyEntity.setDeparture(LocalDate.now());
        journeyEntity.setArrival(LocalDate.now().plusDays(1));

        System.out.println("create journey with id =  " + ticketClient.createJourney(journeyEntity));

        JourneyEntity journeyEntity1 = new JourneyEntity();
        journeyEntity1.setStationFrom("Odessa");
        journeyEntity1.setStationTo("Kimer");
        journeyEntity1.setDeparture(LocalDate.now());
        journeyEntity1.setArrival(LocalDate.now().plusDays(1));

        System.out.println("create journey with id =  " + ticketClient.createJourney(journeyEntity1));
        JourneyEntity journeyEntity2 = new JourneyEntity("Kiev", "Lviv",LocalDate.now(), LocalDate.now().plusDays(1) );
        System.out.println("create journey with id =  " + ticketClient.createJourney(journeyEntity2));




    }
}
