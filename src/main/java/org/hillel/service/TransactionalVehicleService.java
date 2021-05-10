package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;


    @Transactional
    public VehicleEntity createOrUpdate(VehicleEntity vehicleEntity) {
        return vehicleRepository.createOrUpdate(vehicleEntity);
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAll() {
        return vehicleRepository.findAll();
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
    public Collection<VehicleEntity> findAllPageble() {
        return vehicleRepository.findAllPagebleVehicles();
    }

    @Transactional(readOnly = true)
    private Collection<VehicleRepository> findAllVehicles(SearchQueryParam searchQueryParam) {
        return vehicleRepository.findAllAsCriteriaBuildWithParams(searchQueryParam);
    }
}
