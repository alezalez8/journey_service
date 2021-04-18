package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.*;
import org.hillel.persistence.entity.enums.DirectionType;
import org.hillel.persistence.repository.JourneyRepository;
import org.hillel.service.TicketClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Starter {
    public static void main(String[] args) throws Exception {
        final Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.clear(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, 1);

        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        VehicleEntity vehicle1 = buildVehicle("bus1");
        ticketClient.createOrUpdateVehicle(vehicle1);
        VehicleEntity vehicle2 = buildVehicle("bus2");
        ticketClient.createOrUpdateVehicle(vehicle2);
        VehicleEntity vehicle3 = buildVehicle("bus3");
        ticketClient.createOrUpdateVehicle(vehicle3);
        VehicleEntity vehicle4 = buildVehicle("bus4");
        ticketClient.createOrUpdateVehicle(vehicle4);
        VehicleEntity vehicle5 = buildVehicle("bus5");
        ticketClient.createOrUpdateVehicle(vehicle5);
       /* VehicleEntity vehicle2 = buildVehicle("bus2");
        ticketClient.createOrUpdateVehicle(vehicle2);
        VehicleEntity vehicle3 = buildVehicle("bus3");
        ticketClient.createOrUpdateVehicle(vehicle3);*/

        StopEntity stopEntity = buildStop(2D, 3D);


        JourneyEntity journey1 = buildJourney("from 1", "to 1", date, calendar.getTime());
        vehicle1.setName("bus10");
        journey1.addVehicle(vehicle1);
        ticketClient.createOrUpdateJourney(journey1);

       // System.out.println("delete vehicle");
       // ticketClient.removeVehicle(vehicle1);

        //System.out.println(ticketClient.findVehicleById(1L, true));
//        System.out.println(ticketClient.findVehicleByids(1L, 2L,3L,4L,5L));
        System.out.println(ticketClient.findAllVehicles());

       /* System.out.println("delete journey");
        ticketClient.removeById(journey1.getId());*/

        /*journey1.addStop(buildStop(1D, 2D));

        System.out.println("call create journey");
        journey1 = ticketClient.createOrUpdateJourney(journey1);
        journey1.getStops().get(0).setActive(false);
        journey1.addStop(buildStop(2D, 3D));
        System.out.println("call update journey");
        ticketClient.createOrUpdateJourney(journey1);*/

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
        stopEntity.addAddInfo(stopAddInfoEntity);
        return stopEntity;
    }

    private static VehicleEntity buildVehicle(final String name) {
        final VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setName(name);
        return vehicleEntity;
    }


}
