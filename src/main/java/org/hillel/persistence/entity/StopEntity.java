package org.hillel.persistence.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stop")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
public class StopEntity extends AbstractModifyEntity<Long> implements Serializable {

    @Embedded
    private CommonInfo commonInfo;


    @OneToOne(mappedBy = "stop", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private StopAddInfoEntity stopAddInfo;

    @ManyToMany(mappedBy = "stops")
    private List<JourneyEntity> journeys = new ArrayList<>();


    public void addAddInfo(StopAddInfoEntity stopAddInfo) {
        if (stopAddInfo == null) {
            this.stopAddInfo = null;
            return;
        }
        stopAddInfo.setStop(this);
        this.setStopAddInfo(stopAddInfo);
    }

    public void addJourney(JourneyEntity journeyEntity) {
        if (journeyEntity == null) return;
        if (journeys == null) journeys = new ArrayList<>();
        journeys.add(journeyEntity);
    }


}

