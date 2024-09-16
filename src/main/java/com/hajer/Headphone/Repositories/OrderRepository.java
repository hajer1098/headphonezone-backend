package com.hajer.Headphone.Repositories;

import com.hajer.Headphone.Enum.OrderStatus;
import com.hajer.Headphone.Models.Category;
import com.hajer.Headphone.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    Order getUserByIdAndOrderStatus(int userId, OrderStatus orderStatus);
}
