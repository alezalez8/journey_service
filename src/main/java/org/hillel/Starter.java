package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.*;
import org.hillel.persistence.entity.enums.DirectionType;
import org.hillel.service.TicketClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Starter {
    public static void main(String[] args)  throws Exception{
        final Date date = new Date();
        Calendar calendar = new GregorianCalendar();


        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);
        calendar.clear(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, 1);



        JourneyEntity journey1 = buildJourney("from 1", "to 1", date, calendar.getTime());
        journey1.addStop(buildStop(1d, 2d));
        journey1 = ticketClient.createOrUpdateJourney(journey1);



/*
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

        ticketClient.createOrUpdateJourney(journey);*/
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
        final StopAddInfoEntity stopAddInfoEntity = new StopAddInfoEntity();
        stopAddInfoEntity.setLatitude(lat);
        stopAddInfoEntity.setLongitude(lon);
        final StopEntity stopEntity = new StopEntity();
        //-------------------------------------------------
        CommonInfo commonInfo = new CommonInfo();
        commonInfo.setName("stop 1");
        commonInfo.setDescription("stop 1 description");
        stopEntity.setCommonInfo(commonInfo);
        //================================================================
        stopEntity.addAddInfo(stopAddInfoEntity);
        return stopEntity;
    }

    private static VehicleEntity buildVehicle(final String name) {
        final VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setName(name);
        return vehicleEntity;
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
