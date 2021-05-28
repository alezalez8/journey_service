package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stop_additional_info")
@Getter
@Setter
@NoArgsConstructor
public class StopAddInfoEntity extends  AbstractModifyEntity<Long> implements Serializable {

    @Id
    @GeneratedValue()
    private Long id;


    @Column(name = "longitude", nullable = false)
    private double longitude;
    @Column(name = "latitude", nullable = false)
    private double latitude;
    @Column(name = "is_market", length = 100)
    private String market;
    @Column(name = "hotel", length = 100)
    private String hotel;


    @OneToOne
    @MapsId
    @JoinColumn(name = "stop_id")
    private StopEntity stop;

}