package com.hajer.Headphone.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Category")

public class Category extends EntityAbstract{
    @Column(name ="name")
    private String name;

    @Column(name ="description")
    private String description;

}
