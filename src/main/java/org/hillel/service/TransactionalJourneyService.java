package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;


@Service
public class TransactionalJourneyService {

    @Autowired
    private JourneyRepository journeyRepository;


    @Transactional
    public Long createJourney(final JourneyEntity entity) throws IOException {
        if (entity == null) {
            throw new IOException("Unable to create new record");
        }
        return journeyRepository.create(entity);
    }
}