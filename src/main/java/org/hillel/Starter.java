package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.*;
import org.hillel.persistence.entity.enums.DirectionType;
import org.hillel.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        VehicleEntity bus1 = buildVehicle("City 75");
        bus1 = ticketClient.createOrUpdateVehicle(bus1);
        VehicleEntity bus2 = buildVehicle("City 34");
        bus2 = ticketClient.createOrUpdateVehicle(bus2);
        VehicleEntity bus3 = buildVehicle("City 35");
        bus3 = ticketClient.createOrUpdateVehicle(bus3);
        VehicleEntity bus4 = buildVehicle("City 88");
        bus4 = ticketClient.createOrUpdateVehicle(bus4);
        VehicleEntity bus5 = buildVehicle("City 82");
        bus5 = ticketClient.createOrUpdateVehicle(bus5);
        VehicleEntity bus6 = buildVehicle("City 95");
        bus6 = ticketClient.createOrUpdateVehicle(bus6);

        // create journey with stops:
        JourneyEntity journey1 = buildJourney("Odessa", "Lviv", date, calendar.getTime());
        journey1.addStop(buildStop(3D, 5D, "Одесская область", "Умань", "г. Умань",
                "Сельпо", " "));
        journey1.addStop(buildStop(5D, 5D, "Хмельницкая область", "Хмельницкий", "г. Хмельницк",
                "Копейка", "Звезда"));
        journey1.addStop(buildStop(6D, 7D, "Тернопольская область", "Тернопольская", "г. Тернополь",
                "АТБ", "Терноп"));

        JourneyEntity journey2 = buildJourney("Odessa", "Dnipro", date, calendar.getTime());
        journey2.addStop(buildStop(3D, 5D, "Николаевская область", "Николаев", "г. Николаев",
                "Фуршет", " "));
        journey2.addStop(buildStop(7D, 8D, "Криворожская область", "Кривой Рог", "г. Кривой Рог",
                "АТБ", "Криворож"));
        journey2.addStop(buildStop(7D, 9D, "Днипровская область", "Днипровская", "г. Днипро",
                "Селянка", "Днипро"));


        // create journey with vehicle
        journey1.setVehicle(bus1);
        journey1.setVehicle(bus2);
        journey2.setVehicle(bus3);
        journey2.setVehicle(bus4);
        journey1 = ticketClient.createOrUpdateJourney(journey1);
        journey2 = ticketClient.createOrUpdateJourney(journey2);


        // create free seats
        bus1.addFreeSeats(buildFreeSeats(journey1, bus1, 34));
        bus1 = ticketClient.createOrUpdateVehicle(bus1);
        bus2.addFreeSeats(buildFreeSeats(journey1, bus2, 27));
        bus2 = ticketClient.createOrUpdateVehicle(bus2);
        bus3.addFreeSeats(buildFreeSeats(journey1, bus3, 12));
        bus3 = ticketClient.createOrUpdateVehicle(bus3);
        bus4.addFreeSeats(buildFreeSeats(journey2, bus4, 15));
        bus4 = ticketClient.createOrUpdateVehicle(bus4);
        bus5.addFreeSeats(buildFreeSeats(journey2, bus5, 20));
        bus5 = ticketClient.createOrUpdateVehicle(bus5);
        bus6.addFreeSeats(buildFreeSeats(journey2, bus6, 22));
        bus6 = ticketClient.createOrUpdateVehicle(bus6);
        System.out.println('\n');


        System.out.println(ticketClient.findAllVehiclesJpa());
        System.out.println(ticketClient.findByName("City 88"));
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(ticketClient.findAllStops());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(ticketClient.findVehicleName("City 88"));
        System.out.println(ticketClient.findMinSeats());

        // All find by criteria builder
        /*System.out.println("=========== find All by criteria builder ===============");
        System.out.println(ticketClient.findAllVehicleAsCriteria());
        System.out.println(ticketClient.findAllJourneyAsCriteria());*/



       /* System.out.println("------------------findAllVehicles-------------------------------------" + '\n');
        System.out.println(ticketClient.findAllVehicles(2, 3, "active", false));
        System.out.println("------------------findAllStops----------------------------------------" + '\n');
        System.out.println(ticketClient.findAllStops(1, 3, "id", true));
        System.out.println("------------------findAllJourneys-------------------------------------" + '\n');
        System.out.println(ticketClient.findAllJourneys(0, 3, "id", true));


        System.out.println("-------------------find MIN -------------------------------------------" + '\n');
        System.out.println(ticketClient.findMinSeats());
        System.out.println("-------------------find MAX -------------------------------------------" + '\n');
        System.out.println(ticketClient.findMaxSeats());*/
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