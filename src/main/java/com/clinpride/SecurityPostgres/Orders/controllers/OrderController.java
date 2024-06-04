package com.clinpride.SecurityPostgres.Orders.controllers;

import com.clinpride.SecurityPostgres.Orders.models.OrderModels;
import com.clinpride.SecurityPostgres.Orders.services.OrderServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RestController
@RequestMapping("/api/v1/add-to-orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServices orderServices;

    @PostMapping("/createOrder")
    public String saveOrder(@RequestBody OrderModels order) {
        return orderServices.createOrder(order);
    }
    @GetMapping("/find-order/{id}")
    public ResponseEntity<OrderModels> getOrderById(@PathVariable String id) {
        OrderModels order = orderServices.getOneOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find-order-customer-email")
    public ResponseEntity<List<OrderModels>> getOrdersByCustomerEmail(@RequestParam String email) {
        List<OrderModels> orders = orderServices.getOrderByCustomerEmail(email);
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/find-order-order-id")
    public ResponseEntity<OrderModels> getOrdersByOrderId(@RequestParam String orderId) {
        OrderModels order = orderServices.getOneOrderByOrderId(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/delete-order-by-id/{id}")
    public ResponseEntity<String> deleteOderById(@PathVariable String id) {
        boolean isDeleted = orderServices.deleteOrder(id);
        if (isDeleted) {
            String response = "Oder with ID " + id + " deleted successfully.";
            return ResponseEntity.ok(response);
        } else {
            String response = "Order with ID " + id + " does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);        }
    }

    @PostMapping("/edit-order-by-id/{Id}")
    public ResponseEntity<OrderModels> updateOrderById(@PathVariable String Id, @RequestBody OrderModels updatedOrder) {
        Optional<OrderModels> result = orderServices.editByOrderId(Id, updatedOrder);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
