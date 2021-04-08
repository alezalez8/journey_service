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
    public static void main(String[] args) throws Exception {
        final Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.clear(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, 1);

        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        JourneyEntity journey1 = buildJourney("from 1", "to 1", date, calendar.getTime());
        journey1.addStop(buildStop(1D, 2D));
        journey1 = ticketClient.createOrUpdateJourney(journey1);
        journey1.addStop(buildStop(2D, 3D));
        ticketClient.createOrUpdateJourney(journey1);

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
