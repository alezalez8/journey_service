package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class TransactionalJourneyService implements JourneyService{

    @Autowired
    private JourneyRepository journeyRepository;

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        return null;
    }

    @Transactional
    public Long createJourney(final JourneyEntity entity) {
        //todo check
        return journeyRepository.create(entity);

    }
}
