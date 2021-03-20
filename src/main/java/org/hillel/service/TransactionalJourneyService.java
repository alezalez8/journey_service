package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransactionalJourneyService {

    @Autowired
    private JourneyRepository journeyRepository; // inject my repository



    @Transactional
    public Long createJourney(final JourneyEntity entity) {
        //todo check
        return journeyRepository.create(entity);
    }
}
// create our journey using by our repo