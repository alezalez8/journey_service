package org.hillel.service;

import org.hibernate.cfg.Environment;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleFreeSeatsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
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

    //@Autowired
    private Environment environment;

    @Value("${datasource.url}")
    private String url;

    public TicketClient() {
    }


    //=============================================== findAllVehiclesJpa ====================================
    public Collection<VehicleEntity> findAllVehiclesJpa() {
        return vehicleService.findAllVehiclesJpa();
    }


    public Collection<VehicleEntity> findVehicleName(String name) {
        return vehicleService.findVehicleName(name);
    }

    public Collection<VehicleEntity> findMinSeats() {
        return vehicleService.findVehicleWithMinSeats();
    }

    public Collection<VehicleEntity> findVehiclesByCondition(String name) {
        return vehicleService.findByCondition(name);
    }


    public Collection<StopEntity> findAllStops() {
        return stopService.findAll();
    }

    public Collection<JourneyEntity> findAllJourneys() {
        return journeyService.findAll();
    }

    public Collection<JourneyEntity> findAllBySpecification(String from, String to) {
        return journeyService.findAllBySpecification(from, to);
    }


// ============================== old methods =================================================

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


}
