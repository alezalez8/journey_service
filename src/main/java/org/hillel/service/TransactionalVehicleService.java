package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.entity.VehicleEntity_;
import org.hillel.persistence.jpa.repository.VehicleJpaRepository;
import org.hillel.persistence.jpa.repository.specification.VehicleSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleJpaRepository vehicleJpaRepository;


    // ==================================== get transaction with use jpa ====================================

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllVehiclesJpa() {
        return vehicleJpaRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findVehicleName(String name) {
        return vehicleJpaRepository.findAll(VehicleSpecification.byName(name));
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findVehicleWithMinSeats() {
        return vehicleJpaRepository.findVehicleWithMinSeats();
    }


    @Transactional(rollbackFor = IllegalArgumentException.class)
    public VehicleEntity createOrUpdate(VehicleEntity vehicleEntity) {

        return vehicleJpaRepository.save(vehicleEntity);
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findByCondition(String name) {
        final Collection<VehicleEntity> byName = vehicleJpaRepository.findByConditions(
                name,
                1l,
                4l,
                PageRequest.of(0, 2, Sort.by(VehicleEntity_.NAME)));
          return byName;

    }
}
