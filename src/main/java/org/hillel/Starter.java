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
import java.sql.SQLException;
import java.time.LocalDate;

public class Starter {
    public static void main(String[] args) throws SQLException {

        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);

        final JourneyService journeyService = applicationContext.getBean(JourneyService.class);

        System.out.println(journeyService.find("Lviv", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));


    }
}
