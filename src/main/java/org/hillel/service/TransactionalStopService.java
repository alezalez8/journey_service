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
    public StopEntity createOrUpdate(StopEntity stopEntity) {
        return stopRepository.createOrUpdate(stopEntity);
    }


    // end of lesson 5

    /*public Long createStop(final StopEntity stopEntity)  {
        if (stopEntity == null) {
            throw new IllegalArgumentException("Unable to create new record");
        }
        return stopRepository.create(stopEntity);
    }*/
}