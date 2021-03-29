package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.hillel.persistence.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransactionalStopService {

    @Autowired
    private StopRepository stopRepository;


    @Transactional
    public Long createStop(final StopEntity entity)  {
        if (entity == null) {
            throw new IllegalArgumentException("Unable to create new record");
        }
        return stopRepository.create(entity);
    }
}