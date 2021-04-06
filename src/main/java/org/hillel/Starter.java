package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.context.AppContext;
import org.hillel.persistence.entity.*;
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
import java.util.Optional;

public class Starter {
    public static void main(String[] args) {
        final Date date = new Date();
        Calendar calendar = new GregorianCalendar();


        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);
        calendar.clear(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, 1);

        JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom("Odessa");
        journeyEntity.setStationTo("Antalia");
        journeyEntity.setDeparture(date);
        journeyEntity.setDirection(DirectionType.UNKNOW);
        journeyEntity.setArrival(calendar.getTime());
        journeyEntity.setActive(false);

        final VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setName("bus1");
        journeyEntity.addVehicle(vehicleEntity);


        StopAdditionalInfoEntity stopAdditionalInfoEntity = new StopAdditionalInfoEntity();
        stopAdditionalInfoEntity.setLatitude(10D);
        stopAdditionalInfoEntity.setLongitude(176D);
        StopEntity stopEntity = new StopEntity();
//        stopEntity.addStopAdditionalInfo(stopAdditionalInfoEntity);
        CommonInfo commonInfo = new CommonInfo();
        commonInfo.setName("stop 1");
        commonInfo.setDescription("stop 1 description");
        stopEntity.setCommonInfo(commonInfo);
        journeyEntity.addStop(stopEntity);
//        ticketClient.createStop(stopEntity);
        ticketClient.createOrUpdateJourney(journeyEntity);
        final Optional<JourneyEntity> journeyById = ticketClient.getJourneyById(journeyEntity.getId(), true);
//        System.out.println("get all stop by journey " + journeyById.get().getStops() );
        final JourneyEntity journey = journeyById.get();
        System.out.println("get all stop by journey " + journey.getStops());
        System.out.println("create journey with id =  " + journeyById);

        journey.setDirection(DirectionType.TO);
        System.out.println("save journey ");

        ticketClient.createOrUpdateJourney(journey);
    }

    private static JourneyEntity buildJourney(final String stationFrom, final String stationTo,
                                              final Date departure, final Date arrival) {
        final JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom(stationFrom);
        journeyEntity.setStationTo(stationTo);
        journeyEntity.setDeparture(departure);
        journeyEntity.setArrival(arrival);
        journeyEntity.setDirection(DirectionType.TO);
        journeyEntity.setActive(true);
        return journeyEntity;
    }

    private static StopEntity buildStop(final Double lat, final Double lon) {
        StopAdditionalInfoEntity stopAddInfoEntity = new StopAdditionalInfoEntity();
        stopAddInfoEntity.setLatitude(lat);
        stopAddInfoEntity.setLongitude(lon);
        StopEntity stopEntity = new StopEntity();
        stopEntity.addStopAdditionalInfo(stopAddInfoEntity);
        return stopEntity;
    }




/*
        //--------------------------------------------------------------------------------------
        JourneyEntity journeyEntity1 = new JourneyEntity();
        journeyEntity1.setStationFrom("Odessa");
        journeyEntity1.setStationTo("Kimer");
        journeyEntity1.setDeparture(date);
        calendar.clear(Calendar.DAY_OF_YEAR);
        calendar.getTime();
        calendar.set(Calendar.DAY_OF_YEAR, 2);
        journeyEntity1.setArrival(calendar.getTime());
        journeyEntity.setActive(false);
        System.out.println("create journey with id =  " + ticketClient.createJourney(journeyEntity1));

        //--------------------------------------------------------------------------------------

        JourneyEntity journeyEntity2 = new JourneyEntity();
        journeyEntity2.setStationFrom("Kiev");
        journeyEntity2.setStationTo("Lviv");
        journeyEntity2.setDeparture(date);
        calendar.clear(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, 3);
        journeyEntity2.setArrival(calendar.getTime());
        journeyEntity.setActive(true);
        System.out.println("create journey with id =  " + ticketClient.createJourney(journeyEntity2));
*/


}
