package org.hillel.service;

import org.hibernate.cfg.Environment;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleFreeSeatsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Component
public class TicketClient {

    //@Autowired
    private List<JourneyService> journeyServices;

    @Autowired
    private TransactionalJourneyService journeyService;

    @Autowired
    private TransactionalStopService stopService;

    @Autowired
    private TransactionalVehicleService vehicleService;


    @Autowired
    private TranscactionalVehicleSeatsService vehicleSeatsService;

    //    @Autowired
    private Environment environment;

    @Value("${datasource.url}")
    private String url;

    public TicketClient() {
    }


    public Optional<JourneyEntity> getJourneyById(Long id, boolean withDependencies) {
        return id == null ? Optional.empty() : journeyService.findById(id, withDependencies);
    }

    public StopEntity createOrUpdateStop(final StopEntity stopEntity) {
        return stopService.createOrUpdate(stopEntity);
    }

    public JourneyEntity createOrUpdateJourney(JourneyEntity journey) {
        //todo check
        return journeyService.createOrUpdateJourney(journey);
    }


    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) throws Exception {
        if (!StringUtils.hasText(stationFrom))
            throw new IllegalArgumentException("stationFrom must be set");
        if (!StringUtils.hasText(stationTo))
            throw new IllegalArgumentException("stationTo must be set");
        if (dateFrom == null)
            throw new IllegalArgumentException("dateFrom must be set");
        if (dateTo == null)
            throw new IllegalArgumentException("dateTo must be set");

        for (JourneyService service : journeyServices) {
            System.out.println(service.getClass().getName());
        }

        return Collections.emptyList();
    }


    public VehicleEntity createOrUpdateVehicle(VehicleEntity vehicle) {
        return vehicleService.createOrUpdate(vehicle);

    }

    public VehicleFreeSeatsEntity createOrUpdateSeats(final VehicleFreeSeatsEntity freeSeatsEntity) {
        return vehicleSeatsService.createOrUpdate(freeSeatsEntity);
    }

    public void remove(JourneyEntity journey) {
        journeyService.remove(journey);
    }

    public void removeById(Long journeyId) {
        journeyService.removeById(journeyId);
    }

    // =========================== search by params ================================

    public Collection<VehicleEntity> findAllVehicles(int fromPage, int maxResult,
                                                     String sortBy, boolean isAscSort) {
        SearchQueryParam searchQueryParam = new SearchQueryParam(fromPage, maxResult, sortBy, isAscSort);
        return vehicleService.findAllVehicles(searchQueryParam);
    }

    public Collection<JourneyEntity> findAllJourneys(int fromPage, int maxResult,
                                                     String sortBy, boolean isAscSort) {
        SearchQueryParam searchQueryParam = new SearchQueryParam(fromPage, maxResult, sortBy, isAscSort);
        return journeyService.findAllVehicles(searchQueryParam);
    }

    public Collection<StopEntity> findAllStops(int fromPage, int maxResult,
                                               String sortBy, boolean isAscSort) {
        SearchQueryParam searchQueryParam = new SearchQueryParam(fromPage, maxResult, sortBy, isAscSort);
        return stopService.findAllVehicles(searchQueryParam);
    }

    // ============================ searh MIN, MAX vehicle's seats ==============================
    public Collection<VehicleEntity> findMinSeats() {
        return vehicleService.findVehicleWithMinSeats();
    }

    public Collection<VehicleEntity> findMaxSeats() {
        return vehicleService.findVehicleWithMaxSeats();
    }

    //============================= As HQL =======================
    public Collection<VehicleEntity> findAllVehicle() {
        return vehicleService.findAll();
    }

    public Collection<JourneyEntity> findAllJourney() {
        return journeyService.findAll();
    }

    //============================= As Native QL =======================
    public Collection<VehicleEntity> findAllVehicleAsNative() {
        return vehicleService.findAllAsNative();
    }

    public Collection<JourneyEntity> findAllJourneysAsNative() {
        return journeyService.findAllAsNative();
    }

    //============================= As Criteria Builder =======================
    public Collection<VehicleEntity> findAllVehicleAsCriteria() {
        return vehicleService.findAllAsCriteria();
    }

    public Collection<JourneyEntity> findAllJourneyAsCriteria() {
        return journeyService.findAllAsCriteria();
    }

    //============================= As Named =======================
    public Collection<VehicleEntity> findAllVehicleAsNamed() {
        return vehicleService.findAllAsNamed();
    }

    public Collection<JourneyEntity> findAllJorneyAsNamed() {
        return journeyService.findAllAsNamed();
    }

    public Collection<StopEntity> findAllStopAsNamed() {
        return stopService.findAllAsNamed();
    }

    public Collection<VehicleFreeSeatsEntity> findAllSeatAsNamed() {
        return vehicleSeatsService.findAllAsNamed();
    }

    //============================= As Stored Procedure  =======================

    public Collection<VehicleEntity> findAllVehicleAsStoredProcedure() {
        return vehicleService.findAllAsStoredProcedure();
    }

    public Collection<JourneyEntity> findAllJourneyAsStoredProcedure() {
        return journeyService.findAllAsStoredProcedure();
    }

    //=============================================== findAllVehiclesJpa
    public Collection<VehicleEntity> findAllVehiclesJpa() {
        return vehicleService.findAllVehiclesJpa();
    }

    public Collection<VehicleEntity> findByName(String name) {
        return vehicleService.findByName(name);
    }


}
