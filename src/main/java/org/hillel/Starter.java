package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.context.AppContext;
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

         JourneyService journeyService = applicationContext.getBean(JourneyService.class);

        System.out.println(journeyService.find("Lviv", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));
        journeyService = applicationContext.getBean(JourneyService.class);

        System.out.println(journeyService.find("Kiev", "Lviv", LocalDate.now(), LocalDate.now().plusDays(1)));
        journeyService = applicationContext.getBean(JourneyService.class);

        System.out.println(journeyService.find("Kiev", "Lviv", LocalDate.now(), LocalDate.now().plusDays(1)));
        System.out.println(journeyService.find("Kiev", "Lviv", LocalDate.now(), LocalDate.now().plusDays(1)));



    }
}
