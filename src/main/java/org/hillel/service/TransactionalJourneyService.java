package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.jpa.repository.JourneyJpaRepository;
import org.hillel.persistence.jpa.repository.specification.JourneySpecification;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;


@Service(value = "transactionalJourneyService")
public class TransactionalJourneyService {

    @Autowired
    private JourneyRepository journeyRepository;


    @Autowired
    private JourneyJpaRepository journeyJpaRepository;


    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAll() {
        return journeyJpaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAllBySpecification(String from, String to) {
        return journeyJpaRepository.findAll(JourneySpecification.allJorneysBySpecification(from, to));
    }






    @Transactional
    public JourneyEntity createOrUpdateJourney(final JourneyEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Unable to create new record");
        }

        return journeyRepository.createOrUpdate(entity);

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


    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAllVehicles(SearchQueryParam searchQueryParam) {
        return journeyRepository.findAllAsCriteriaBuildWithParams(searchQueryParam);
    }

    @Transactional
    public void remove(JourneyEntity journey) {
        journeyRepository.remove(journey);
    }

    @Transactional
    public void removeById(Long journeyId) {
        journeyRepository.removeById(journeyId);
    }

    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAllAsNative(){
        return journeyRepository.findAllAsNative();
    }

    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAllAsCriteria() {
        return journeyRepository.findAllAsCriteria();
    }

    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAllAsNamed(){
        return journeyRepository.findAllAsNamed();
    }

    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAllAsStoredProcedure() {
        return journeyRepository.findAllAsStoredProcedure();
    }
}