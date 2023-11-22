package com.clinpride.SecurityPostgres.Orders.repo;

import com.clinpride.SecurityPostgres.Orders.models.OrderModels;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends MongoRepository<OrderModels, String> {
    List<OrderModels> findByCustomerEmail(String email);
    Optional<OrderModels> findByOrderId(String orderId);
}