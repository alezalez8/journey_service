package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.jpa.repository.StopJpaRepository;
import org.hillel.persistence.repository.JourneyRepository;
import org.hillel.persistence.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Service
public class TransactionalStopService {

    /*@Autowired
    private StopRepository stopRepository;
    */
    @Autowired
    private StopJpaRepository stopRepository;



    @Transactional
    public StopEntity createOrUpdate(StopEntity stopEntity) {
        return stopRepository.save(stopEntity);
    }

    @Transactional(readOnly = true)
    public Collection<StopEntity> findAll (){
        return stopRepository.findAll();
    }

  /*  @Transactional(readOnly = true)
    public Collection<StopEntity> findAllAsNamed (){
        return stopRepository.findAllAsNamed();
    }
*/
 /*   @Transactional(readOnly = true)
    public Collection<StopEntity> findAllStops(SearchQueryParam searchQueryParam) {
        return stopRepository.findAll(searchQueryParam);
    }*/

}
