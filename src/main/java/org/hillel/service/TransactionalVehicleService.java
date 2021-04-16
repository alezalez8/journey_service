package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;


    @Transactional
    public VehicleEntity createOrUpdate(VehicleEntity vehicleEntity) {
        return vehicleRepository.createOrUpdate(vehicleEntity);
    }

    @Transactional
    public void remove(VehicleEntity vehicleEntity) {
        vehicleRepository.remove(vehicleEntity);
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findByIds(Long... ids) {
        return vehicleRepository.findByIds(ids);
    }

    @Transactional(readOnly = true) // сущность не будет меняться
    public Optional<VehicleEntity> findById(Long id, boolean withDep) {
        final Optional<VehicleEntity> byId = vehicleRepository.findById(id);
        if (!byId.isPresent()) return byId;
        if (!withDep) return byId;
        byId.get().getJourneys().size();
        return byId;
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAll() {
        return vehicleRepository.findAll();
    }

}
