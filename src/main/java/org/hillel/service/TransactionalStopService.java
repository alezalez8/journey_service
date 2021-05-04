package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.hillel.persistence.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Service
public class TransactionalStopService {

    @Autowired
    private StopRepository stopRepository;


    @Transactional
    public StopEntity createOrUpdate(StopEntity stopEntity) {
        return stopRepository.createOrUpdate(stopEntity);
    }

    @Transactional
    public Collection<StopEntity> findAllAsNamed (){
        return stopRepository.findAllAsNamed();
    }

}
