package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.metadata.HsqlTableMetaDataProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private PlatformTransactionManager platformTransactionManager; // for second method without annotation  @Transactional

    @Autowired
    private EntityManagerFactory entityManagerFactory;  // for second method without annotation  @Transactional

    @Autowired
    private NewTransactionalVehicleService newTransactionalVehicleService;


    @Transactional
    public VehicleEntity createOrUpdate(VehicleEntity vehicleEntity) {
        //=====================================================================================
        // first method without annotation  @Transactional
//        return transactionTemplate.execute((status) -> vehicleRepository.createOrUpdate(vehicleEntity));
        //=====================================================================================

        // the second method without annotation  @Transactional
        //=====================================================================================
        /*DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        final TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
        vehicleRepository.createOrUpdate(vehicleEntity);
        platformTransactionManager.commit(transaction);
        platformTransactionManager.rollback(transaction); // в случае экспешна двух верхних строк выполняется откат
        return null;*/
        //=====================================================================================


        // the third method without annotation  @Transactional, uses entityManager
        //=====================================================================================
        /*EntityManager em = entityManagerFactory.createEntityManager();
        final EntityTransaction transaction1 = em.getTransaction();
        transaction1.begin();
        em.persist(vehicleEntity);
        transaction1.commit();
        // если неуспешно, то откат:
        transaction1.rollback();
        return null;*/
        //=====================================================================================


        // standart method with annotation  @Transactional
       return vehicleRepository.createOrUpdate(vehicleEntity); // standart method with annotation  @Transactional
    }

    @Transactional
    public void remove(VehicleEntity vehicleEntity) {
        vehicleRepository.remove(vehicleEntity);
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findByIds(Long... ids) {
        return vehicleRepository.findByIds(ids);
    }

    @Transactional(readOnly = true) // сущность не будет меняться
    public Optional<VehicleEntity> findById(Long id, boolean withDep) {
        final Optional<VehicleEntity> byId = vehicleRepository.findById(id);
        if (!byId.isPresent()) return byId;
        if (!withDep) return byId;
        byId.get().getJourneys().size();
        return byId;
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAll() {
        return vehicleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findByName(String name) {
        return vehicleRepository.findByName(name);
    }

    @Transactional(rollbackFor = IllegalArgumentException.class)
//    @Transactional()
    public Collection<VehicleEntity> findAllByName(String name) {
        final Collection<VehicleEntity> byName = vehicleRepository.findByName(name);
        final VehicleEntity next = byName.iterator().next();
        next.setName(String.valueOf(System.currentTimeMillis()));
        System.out.println("save vehicle id = " + next.getId() + " and new value " + next.getName());
        newTransactionalVehicleService.createOrUpdate(next);
        /*if (true) {
            throw new IllegalArgumentException("new exception");
        }*/
        return byName;

    }


    /*@Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllByName(String name) {
         return vehicleRepository.findByName(name);

    }*/

}
