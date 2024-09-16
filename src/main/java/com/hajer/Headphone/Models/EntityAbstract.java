package com.hajer.Headphone.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@SuperBuilder
//pour pouvoir contruire les obkjets a herité
@MappedSuperclass
//les attribut sont herité par autre classes
@EntityListeners(AuditingEntityListener.class)
public class EntityAbstract {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(

            updatable = false
    )
    private LocalDateTime creationDate;

    @LastModifiedDate

    private LocalDateTime lastUpdate;


}
