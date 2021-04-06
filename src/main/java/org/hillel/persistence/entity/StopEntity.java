package org.hillel.persistence.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stop")
@Getter
@Setter
@NoArgsConstructor
public class StopEntity extends AbstractModifyEntity<Long> implements Serializable {

   /*@Transient                                 //exclude this field
    private boolean applyToJourneyBuild;*/

    @Embedded
    private CommonInfo commonInfo;


    @OneToOne(mappedBy = "stop", cascade = {CascadeType.PERSIST})
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

