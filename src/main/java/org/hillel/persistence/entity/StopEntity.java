package org.hillel.persistence.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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

    @ManyToMany(mappedBy = "stops")
    private List<JourneyEntity> journeys = new ArrayList<>();

    @OneToOne(mappedBy = "stop", cascade = CascadeType.PERSIST)
    private StopAdditionalInfoEntity additionalInfo;

    public void addStopAdditionalInfo(StopAdditionalInfoEntity stopAdditionalInfo) {
        if(stopAdditionalInfo == null) {
            this.additionalInfo = null;
            return;
        }
        stopAdditionalInfo.setStop(this);
        this.additionalInfo = stopAdditionalInfo;
    }

    public void addJourney(JourneyEntity journeyEntity) {
        if(journeyEntity == null) return;
        if(journeys == null) journeys = new ArrayList<>();
        journeys.add(journeyEntity);
    }
}

