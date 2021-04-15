package org.hillel.persistence.repository;


import org.hillel.persistence.entity.StopEntity;
import org.springframework.stereotype.Repository;


@Repository
public class StopRepository extends CommonRepository<StopEntity, Long> {

    protected StopRepository() {
        super(StopEntity.class);
    }


    @Override
    public void remove(StopEntity stop) {
        stop = findById(stop.getId()).get();
        stop.removeAllJorneys();
//        getEntityManager().remove(stop.getStopAddInfo());
        super.remove(stop);

    }


}