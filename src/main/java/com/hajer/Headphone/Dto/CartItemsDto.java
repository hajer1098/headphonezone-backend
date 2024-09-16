package com.hajer.Headphone.Dto;


import com.hajer.Headphone.Models.CartItems;
import com.hajer.Headphone.Models.Category;
import com.hajer.Headphone.Models.Product;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CartItemsDto {

    private Integer id;

    private float price;

    private Long quantity;

    private Integer productId;

    private String productName;

    private Integer orderId;

    private byte[] retunedImg;

    private Integer userId;

    public  static  CartItemsDto fromEntity(CartItems cartItems){
        //null check
        return CartItemsDto.builder()
                .id(cartItems.getId())
                .price(cartItems.getPrice())
                .quantity(cartItems.getQuantity())
                .productId(cartItems.getProduct().getId())
                .productId(cartItems.getProduct().getId())
                .productName(cartItems.getProduct().getName())
                .orderId(cartItems.getOrder().getId())
                .retunedImg(cartItems.getProduct().getImage())
                .userId(cartItems.getUser().getId())
                .build();
    }



}
