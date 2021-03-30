package org.hillel.persistence.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;

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

}

