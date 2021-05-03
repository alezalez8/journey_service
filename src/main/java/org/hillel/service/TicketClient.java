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
import java.util.*;


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
        System.out.println(" =================== createOrUpdateVehicle ====================================");

        return vehicleService.createOrUpdate(vehicle);

    }

    public void remove(JourneyEntity journey) {
        System.out.println(" =================== remove ==============================================");
        journeyService.remove(journey);
    }

    public void removeById(Long journeyId) {
        System.out.println(" =================== removeById ===========================================");
        journeyService.removeById(journeyId);
    }

    public void removeVehicle(final VehicleEntity vehicleEntity) {
        System.out.println(" =================== removeVehicle ===========================================");
        vehicleService.remove(vehicleEntity);
    }

    public Collection<VehicleEntity> findVehicleByids(Long ... ids) {
        System.out.println("id = " + Arrays.toString(ids));
        return vehicleService.findByIds(ids);
    }

    public Optional<VehicleEntity> findVehicleById(Long id, boolean withDependencies) {
        System.out.println(" =================== Method findVehicleById =============================================");
        return vehicleService.findById(id, withDependencies);
    }

    public Collection<VehicleEntity> findAllVehicles() {
        System.out.println(" =================== Method findAllVehicles() ====================================");

        return vehicleService.findAll();
    }

    public Collection<VehicleEntity> findAllVehiclesByName(String name) {
        System.out.println(" =================== Method findAllVehiclesByName(String name) =====================");

        return vehicleService.findByName(name);
    }
}

