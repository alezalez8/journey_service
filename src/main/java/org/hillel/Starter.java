package org.hillel;

import org.hillel.context.AppContext;
import org.hillel.service.JourneyService;

import java.io.IOException;
import java.time.LocalDate;

public class Starter {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        AppContext.load("application.properties");
        final JourneyService journeyService =  AppContext.getBean("journeyService");
        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(1)));
        System.out.println(journeyService.find("Odessa", "Kiev", LocalDate.now(), LocalDate.now().plusDays(3).plusDays(4)));


    }
}
