package com.clinpride.SecurityPostgres.Orders.services;

import com.clinpride.SecurityPostgres.Orders.models.OrderModels;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public interface OrderServices {
    String createOrder(OrderModels orderObject);
    OrderModels getOneOrderById(String id);
    List<OrderModels> getOrderByCustomerEmail(String Email);
    OrderModels getOneOrderByOrderId(String orderId);
    Optional<OrderModels> editByOrderId(String Id, OrderModels product);
    boolean deleteOrder (String id);

}
