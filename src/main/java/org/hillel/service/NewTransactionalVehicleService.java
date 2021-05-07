package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManagerFactory;
import java.util.Collection;
import java.util.Optional;

@Service
public class NewTransactionalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;


//    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = IllegalArgumentException.class)
    @Transactional(propagation = Propagation.REQUIRED)
    public VehicleEntity createOrUpdate(VehicleEntity vehicleEntity) {
        final VehicleEntity orUpdate = vehicleRepository.createOrUpdate(vehicleEntity);
        if(true) {
            throw new IllegalArgumentException("new exception");
        }
        return orUpdate; // standart method with annotation  @Transactional
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

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findByName(String name) {
        return vehicleRepository.findByName(name);
    }


}
