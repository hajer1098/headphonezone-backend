package com.hajer.Headphone.Implements;

import com.hajer.Headphone.Dto.AddProductInCartDto;
import com.hajer.Headphone.Dto.CartItemsDto;
import com.hajer.Headphone.Dto.OrderDto;
import com.hajer.Headphone.Dto.UserDto;
import com.hajer.Headphone.Enum.OrderStatus;
import com.hajer.Headphone.Models.CartItems;
import com.hajer.Headphone.Models.Order;
import com.hajer.Headphone.Models.Product;
import com.hajer.Headphone.Models.User;
import com.hajer.Headphone.Repositories.CartItemsRespository;
import com.hajer.Headphone.Repositories.OrderRepository;
import com.hajer.Headphone.Repositories.ProductRepository;
import com.hajer.Headphone.Repositories.UserRepository;
import com.hajer.Headphone.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private OrderRepository orderRepository;

     @Autowired
    private UserRepository userRepository;
    @Autowired

    private ProductRepository productRepository;

    @Autowired
    private CartItemsRespository cartItemsRespository;

    @Override
    public ResponseEntity<?> addProductInCart(AddProductInCartDto addProductInCartDto){
         Order activateOrder =orderRepository.getUserByIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.PENDING);
        Optional<CartItems> optionalCartItems=cartItemsRespository.findByProductIdAndUserIdAndOrderId(
                addProductInCartDto.getProductId(),
                addProductInCartDto.getUserId(),
                activateOrder.getId()
            );

        //if a value is present in the Optional object,
        if(optionalCartItems.isPresent()){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        else{
                 Optional<Product> optionalProduct=productRepository.findById(addProductInCartDto.getProductId());
                 Optional<User> optionalUser=userRepository.findById(addProductInCartDto.getUserId() );

                 if(optionalProduct.isPresent() &&optionalUser.isPresent()){
                     CartItems cartItems =new CartItems();
                     cartItems.setProduct(optionalProduct.get());
                     cartItems.setUser(optionalUser.get());
                     cartItems.setPrice(optionalProduct.get().getPrice());
                     cartItems.setQuantity(1L);
                     cartItems.setOrder(activateOrder);

                     CartItems savedCartItems =cartItemsRespository.save(cartItems);

                     activateOrder.setTotalAmount((activateOrder.getTotalAmount()+ cartItems.getPrice()));
                     activateOrder.setAmount(activateOrder.getAmount()+cartItems.getPrice());
                     //add items/elements to order
                     activateOrder.getCartItems().add(cartItems);

                     orderRepository.save(activateOrder);

                     return ResponseEntity.status(HttpStatus.CREATED).body(cartItems);


                 }
                 else {
                     return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("user  or product not found");


                 }

        }

    }

    public OrderDto getCartByUserId(Integer userId){
        Order activateOrder =orderRepository.getUserByIdAndOrderStatus(userId, OrderStatus.PENDING);

            List<CartItemsDto> cartItemsDtoList =activateOrder
                .getCartItems().stream().map(CartItemsDto::fromEntity)
                .collect(Collectors.toList());

        OrderDto orderDto=new OrderDto();

        orderDto.setId(activateOrder.getId());
        orderDto.setAmount(activateOrder.getAmount());
        orderDto.setOrderStatus(activateOrder.getOrderStatus());
        orderDto.setDiscount(activateOrder.getDiscount());
        orderDto.setTotalAmount(activateOrder.getTotalAmount());
        orderDto.setCartItems(cartItemsDtoList);
        return orderDto;
    }





}
