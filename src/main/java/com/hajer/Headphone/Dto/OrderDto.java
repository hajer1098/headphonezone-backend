package com.hajer.Headphone.Dto;


import com.hajer.Headphone.Enum.OrderStatus;
import com.hajer.Headphone.Models.CartItems;
import com.hajer.Headphone.Models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {

    private Integer id;
    private String orderDescription;
    private Date date;
    private float amount;
    private String address;
    private OrderStatus orderStatus;
    private float TotalAmount;
    private float discount;
    private UUID trackkingId;


    private String  userName;
    //one order can contain many articles

    private List<CartItemsDto> cartItems;

}
