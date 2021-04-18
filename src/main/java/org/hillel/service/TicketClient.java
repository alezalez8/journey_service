package org.hillel.service;

import org.hibernate.cfg.Environment;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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

  // @Autowired
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

    public void remove(JourneyEntity journey) {
        journeyService.remove(journey);
    }

    public void removeById(Long journeyId) {
        journeyService.removeById(journeyId);
    }

    public void removeVehicle(final VehicleEntity vehicleEntity) {
        vehicleService.remove(vehicleEntity);
    }

    public Collection<VehicleEntity> findVehicleByids(Long ... ids) {
        return vehicleService.findByIds();
    }

    public Optional<VehicleEntity> findVehicleById(Long id, boolean withDependencies) {
        return vehicleService.findById(id, withDependencies);
    }

    public Collection<VehicleEntity> findAllVehicles() {
        return vehicleService.findAll();
    }

    public Collection<VehicleEntity> findAllVehiclesByName(String name) {
        return vehicleService.findByName(name);
    }
}

