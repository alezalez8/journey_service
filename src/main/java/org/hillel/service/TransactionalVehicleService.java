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
    public void  remove(VehicleEntity vehicleEntity) {
        vehicleRepository.remove(vehicleEntity);
    }

    @Transactional
    public Collection<VehicleEntity> findByIds(Long... ids) {
        return vehicleRepository.findByIds(ids);
    }
    @Transactional
    public Optional<VehicleEntity> findById(Long id) {
        return vehicleRepository.findById(id);
    }


}
