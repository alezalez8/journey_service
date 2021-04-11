package org.hillel.service;

import org.hillel.persistence.entity.VehicleFreeSeats;
import org.hillel.persistence.repository.VehicleSeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TranscactionalVehicleSeatsService {

    @Autowired
    private VehicleSeatsRepository seatsRepository;

    @Transactional
    public VehicleSeatsRepository createOrUpdate(final VehicleFreeSeats freeSeats) {
        return seatsRepository.createOrUpdate(freeSeats);
    }
}
