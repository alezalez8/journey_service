package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransactionalJourneyService {

    @Autowired
    private JourneyRepository journeyRepository;


    @Transactional
    public Long createJourney(final JourneyEntity entity) {
        if(entity != null) {
            return journeyRepository.create(entity);
        }

        return journeyRepository.create(entity);
    }
}