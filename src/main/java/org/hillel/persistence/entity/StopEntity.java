package org.hillel.persistence.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stop")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
public class StopEntity extends AbstractModifyEntity<Long> implements Serializable {

    @Embedded
    private CommonInfo commonInfo;

    @Column(name = "name_location", nullable = false, length = 100)
    private String nameLocation;


    @OneToOne(mappedBy = "stop", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private StopAddInfoEntity stopAddInfo;

    @ManyToMany(mappedBy = "stops")
    private List<JourneyEntity> journeys = new ArrayList<>();

    public void removeAllJorneys() {
        if (Objects.isNull(journeys)) return;
        for (JourneyEntity temp : journeys
        ) {
            temp.getStops().remove(this);
        }
        this.journeys.clear();
    }


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

    @Override
    public String toString() {
        return "NameLocation  " + getNameLocation();
    }
}
