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

public class CartItems extends EntityAbstract{


        private float price;

        private Long quantity;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "product_id",nullable = false)
        @OnDelete(action= OnDeleteAction.CASCADE)
        private Product product;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "user_id",nullable = false)
        @OnDelete(action= OnDeleteAction.CASCADE)
        private  User user;

        @ManyToOne(fetch =FetchType.LAZY)
        @JoinColumn(name = "order_id")
        private Order order;


    }


