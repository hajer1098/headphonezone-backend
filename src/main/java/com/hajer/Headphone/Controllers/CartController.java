package com.hajer.Headphone.Controllers;


import com.hajer.Headphone.Dto.AddProductInCartDto;
import com.hajer.Headphone.Services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartes")
@RequiredArgsConstructor

public class CartController {

    private final CartService cartService;

   @PostMapping("/cart")
    public ResponseEntity<?> AddProductInCart(@RequestBody AddProductInCartDto addProductInCartDto){
        return ResponseEntity.ok(cartService.addProductInCart(addProductInCartDto));
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Integer userId){
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }
}

