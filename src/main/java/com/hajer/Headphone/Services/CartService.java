package com.hajer.Headphone.Services;

import com.hajer.Headphone.Dto.AddProductInCartDto;
import com.hajer.Headphone.Dto.OrderDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    public ResponseEntity<?> addProductInCart(AddProductInCartDto addProductInCartDto);
    public OrderDto getCartByUserId(Integer userId);

}
