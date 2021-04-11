package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class CommonInfo {

    @Column(name = "name") // remove : nullable = false
    private String name;

    @Column(name = "description", length = 10000)
    private String description;
}