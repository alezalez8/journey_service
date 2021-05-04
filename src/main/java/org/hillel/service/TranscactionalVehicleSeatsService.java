package org.hillel.service;

import org.hillel.persistence.entity.VehicleFreeSeatsEntity;
import org.hillel.persistence.repository.VehicleSeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class TranscactionalVehicleSeatsService {

    @Autowired
    private VehicleSeatsRepository seatsRepository;

    @Transactional
    public VehicleFreeSeatsEntity createOrUpdate(final VehicleFreeSeatsEntity freeSeats) {
        return seatsRepository.createOrUpdate(freeSeats);
    }

    @Transactional
    public Collection<VehicleFreeSeatsEntity> findAllAsNamed() {
        return seatsRepository.findAllAsNamed();
    }

}
