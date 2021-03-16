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
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {

        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        // JourneyService journeyService = applicationContext.getBean(JourneyService.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);
        System.out.println(ticketClient.find("Lviv", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));

        JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom("Kiev");
        System.out.println("create journey with id =  " + ticketClient.createJourney(journeyEntity));



    }
}
