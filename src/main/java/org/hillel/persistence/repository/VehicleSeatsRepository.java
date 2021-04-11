package org.hillel.persistence.repository;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hillel.persistence.entity.AbstractModifyEntity;
import org.hillel.persistence.entity.VehicleFreeSeats;
import org.hillel.persistence.entity.VehicleFreeSeatsEntity;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public class VehicleSeatsRepository  {
    public VehicleSeatsRepository createOrUpdate(VehicleFreeSeats freeSeats) {
        return  null;
    }


  /*  public void createOrUpdate(VehicleFreeSeatsEntity entity) {
        super.createOrUpdate(entity);
    }
    */
}
