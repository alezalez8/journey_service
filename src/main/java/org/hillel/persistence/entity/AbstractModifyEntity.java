package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hillel.persistence.entity.util.YesNoConverter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@MappedSuperclass
public abstract class AbstractModifyEntity<ID> {

    /* @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stopSeq")
    @SequenceGenerator(name = "stopSeq", sequenceName = "stop_seq", allocationSize = 1)
    private Long id; */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date createDate;

    @Column(name = "active")
    @Convert(converter = YesNoConverter.class)
    //@Type(type = "numeric_boolean")
    private boolean active = true;

}
