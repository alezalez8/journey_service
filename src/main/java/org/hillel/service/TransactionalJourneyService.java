package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;


@Service(value = "transactionalJourneyService")
public class TransactionalJourneyService {

    @Autowired
    private JourneyRepository journeyRepository;


    @Transactional
    public Long createJourney(final JourneyEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Unable to create new record");
        }
        return journeyRepository.create(entity);
    }

    @Transactional(readOnly = true)
    public Optional<JourneyEntity> getById(Long id, boolean withDependencies) {
        final Optional<JourneyEntity> byId = journeyRepository.findById(id);
        if (withDependencies && byId.isPresent()) {
            final JourneyEntity journeyEntity = byId.get();
            journeyEntity.getVehicle().getName();  //
            journeyEntity.getStops().size();        // get table in case lazy
        }
        return byId;
    }
    @Transactional
    public void save(JourneyEntity journey) {
//        journeyRepository.save(journey);
        //final JourneyRepository journeyRepository = this.journeyRepository;
        //final JourneyRepository save = journeyRepository.findById(journey.getId()).get(); // the same as merge
        final JourneyEntity save = journeyRepository.save(journey);
        save.setStationFrom("test station from");
    }

}