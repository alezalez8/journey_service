package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;


@Service(value = "transactionalJourneyService")
public class TransactionalJourneyService {

    @Autowired
    private JourneyRepository journeyRepository;


    @Transactional
    public JourneyEntity createOrUpdateJourney(final JourneyEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Unable to create new record");
        }
        System.out.println("create journey ");
        final JourneyEntity orUpdate = journeyRepository.createOrUpdate(entity);
        System.out.println("get journey by id");
        JourneyEntity journey = journeyRepository.findById(orUpdate.getId()).get();
        System.out.println("remove journey by id");
        journeyRepository.removeById(journey.getId());

        //journeyRepository.getEntityManager().flush();


        JourneyEntity entity2 = new JourneyEntity();
        entity2.setArrival(orUpdate.getArrival());
        entity2.setDeparture(orUpdate.getDeparture());
        entity2.setStationFrom(orUpdate.getStationFrom());
        entity2.setStationTo(orUpdate.getStationTo());
        entity2.setActive(false);

        return journeyRepository.createOrUpdate(entity2);

    }

    @Transactional(readOnly = true)
    public Optional<JourneyEntity> findById(Long id, boolean withDependencies) {
        final Optional<JourneyEntity> byId = journeyRepository.findById(id);
        if (withDependencies && byId.isPresent()) {
            final JourneyEntity journeyEntity = byId.get();
            journeyEntity.getVehicle().getName();  //
            journeyEntity.getStops().size();        // get table in case lazy
        }
        return byId;
    }

    @Transactional
    public void remove(JourneyEntity journey) {
        journeyRepository.remove(journey);
    }

    @Transactional
    public void removeById(Long journeyId) {
        journeyRepository.removeById(journeyId);
    }
}