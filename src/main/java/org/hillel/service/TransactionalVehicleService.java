package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;


    @Transactional
    public VehicleEntity createOrUpdate(VehicleEntity vehicleEntity) {
        return vehicleRepository.createOrUpdate(vehicleEntity);
    }
}
