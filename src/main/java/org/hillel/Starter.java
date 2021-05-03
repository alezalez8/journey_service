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

        // create vehicles:
        VehicleEntity busOne = buildVehicle("City 34");
        busOne = ticketClient.createOrUpdateVehicle(busOne);
        VehicleEntity busTwo = buildVehicle("City 88");
        busTwo = ticketClient.createOrUpdateVehicle(busTwo);

        // create journey with stops:
        JourneyEntity journey = buildJourney("Odessa", "Lviv", date, calendar.getTime());
        journey.addStop(buildStop(3D, 5D, "Одесская область","Умань", "г. Умань",
                "Сельпо", " "));
        journey.addStop(buildStop(5D, 5D, "Хмельницкая область","Хмельницкий", "г. Хмельницк",
                "Копейка", "Звезда"));
        journey.addStop(buildStop(6D, 7D, "Тернопольская область","Тернопольская", "г. Тернополь",
                "АТБ", "Терноп"));


        // create journey with vehicle
        journey.setVehicle(busOne);
        journey.setVehicle(busTwo);
        journey = ticketClient.createOrUpdateJourney(journey);

        // create free seats
        busOne.addFreeSeats(buildFreeSeats(journey, busOne, 34));
        busOne = ticketClient.createOrUpdateVehicle(busOne);

        // All find by HQL querry
        //findAllVehicle.


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

    private static StopEntity buildStop(final Double lat, final Double lon, String nameLocation,
                                        final String name, final String description,
                                        final String market, final String hotel) {
        final StopAddInfoEntity stopAddInfoEntity = new StopAddInfoEntity();
        stopAddInfoEntity.setLatitude(lat);
        stopAddInfoEntity.setLongitude(lon);
        stopAddInfoEntity.setMarket(market);
        stopAddInfoEntity.setHotel(hotel);


        final CommonInfo commonInfo = new CommonInfo();
        commonInfo.setName(name);
        commonInfo.setDescription(description);

        final StopEntity stopEntity = new StopEntity();
        stopEntity.setNameLocation(nameLocation);
        stopEntity.addAddInfo(stopAddInfoEntity);
        stopEntity.setCommonInfo(commonInfo);

        return stopEntity;
    }


    /*private static CommonInfo buildAddInfo(final String name, final String description) {
        final CommonInfo commonInfo = new CommonInfo();
        commonInfo.setName(name);
        commonInfo.setDescription(description);
        return commonInfo;
    }*/

    private static VehicleEntity buildVehicle(final String name) {
        final VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setName(name);
        return vehicleEntity;
    }

    private static VehicleFreeSeatsEntity buildFreeSeats(final JourneyEntity journeyEntity,
                                                         final VehicleEntity vehicleEntity,
                                                         Integer freeSeats) {
        final VehicleFreeSeatsEntity freeSeatsEntity = new VehicleFreeSeatsEntity();
        freeSeatsEntity.setJourney(journeyEntity);
        freeSeatsEntity.setVehicleEntity(vehicleEntity);
        freeSeatsEntity.setFreeSeats(freeSeats);
        return freeSeatsEntity;

    }


}