package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.jpa.repository.VehicleJpaRepository;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleJpaRepository vehicleJpaRepository;


    @Transactional
    public VehicleEntity createOrUpdate(VehicleEntity vehicleEntity) {
        return vehicleRepository.createOrUpdate(vehicleEntity);
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAll() {
        return vehicleRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findVehicleWithMinSeats() {
        return vehicleRepository.findVehicleWithMinSeats();
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findVehicleWithMaxSeats() {
        return vehicleRepository.findVehicleWithMaxSeats();
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllAsNative() {
        return vehicleRepository.findAllAsNative();

    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllAsCriteria() {
        return vehicleRepository.findAllAsCriteria();
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllAsNamed() {
        return vehicleRepository.findAllAsNamed();
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllAsStoredProcedure() {
        return vehicleRepository.findAllAsStoredProcedure();
    }


    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllVehicles(SearchQueryParam searchQueryParam) {
        return vehicleRepository.findAllAsCriteriaBuildWithParams(searchQueryParam);
    }

    // ======================================================================================
    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllVehiclesJpa() {

        //VehicleEntity vehicleEntity = new VehicleEntity();
        //vehicleEntity.setName(name);
        return vehicleJpaRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findByName(String name) {
        return vehicleJpaRepository.findByName(name);
    }
}
