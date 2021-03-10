package org.hillel;

import org.hillel.context.AppContext;
import org.hillel.service.JourneyService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.time.LocalDate;

public class Starter {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        // AppContext.load("application.properties");
        //final BeanFactory applicationContext = new  XmlBeanFactory(new ClassPathResource("common-beans.xml"));

        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("common-beans.xml");
        final JourneyService journeyService = applicationContext.getBean(JourneyService.class);

        //final JourneyService journeyService = AppContext.getBean("journeyService");
        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));
        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(0).plusDays(1)));


    }
}
