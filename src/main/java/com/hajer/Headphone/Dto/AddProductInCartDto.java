package com.hajer.Headphone.Dto;


import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddProductInCartDto {

    private Integer userId;

    private Integer productId;
}
