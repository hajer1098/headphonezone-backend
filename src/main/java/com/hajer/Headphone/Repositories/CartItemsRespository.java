package com.hajer.Headphone.Repositories;

import com.hajer.Headphone.Models.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



public interface CartItemsRespository extends JpaRepository<CartItems,Integer> {

   public Optional<CartItems> findByProductIdAndUserIdAndOrderId(Integer productId, Integer userId, Integer orderId);


}
