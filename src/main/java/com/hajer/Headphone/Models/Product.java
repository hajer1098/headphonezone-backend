package com.hajer.Headphone.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Product")

public class Product extends EntityAbstract {
    @Column(name ="name")
    private String name;

    @Column(name ="description")
    private String description;

    @Column(name ="price")
    private float price;



    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id",nullable=false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Category category;

}
