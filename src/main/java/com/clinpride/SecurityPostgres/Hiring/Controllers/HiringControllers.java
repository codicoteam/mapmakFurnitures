package com.clinpride.SecurityPostgres.Hiring.Controllers;

import com.clinpride.SecurityPostgres.Hiring.Services.HiringServices;
import com.clinpride.SecurityPostgres.Hiring.models.HiringModel;
import com.clinpride.SecurityPostgres.Orders.models.OrderModels;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RestController
@RequestMapping("/api/v1/add-hire-products-orders")
@RequiredArgsConstructor
public class HiringControllers {
    private final HiringServices hiringServices;

    @PostMapping("/create-hiring-order")
    public String saveOrder(@RequestBody HiringModel hiringModel) {
        return hiringServices.HireProducts(hiringModel);
    }
    @GetMapping("/find-hiring-order/{id}")
    public ResponseEntity<HiringModel> getOrderById(@PathVariable String id) {
        HiringModel order = hiringServices.getOneHiringById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find-hiring-order-customer-email")
    public ResponseEntity<List<HiringModel>> getOrdersByCustomerEmail(@RequestParam String email) {
        List<HiringModel> orders = hiringServices.getOrderByCustomerEmail(email);
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/find-order-hiring-order-id")
    public ResponseEntity<HiringModel> getOrdersByOrderId(@RequestParam String hiringId) {
        HiringModel order = hiringServices.getOneHiringByOrderId(hiringId);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/delete-order-hiring-by-id/{id}")
    public ResponseEntity<String> deleteOderById(@PathVariable String id) {
        boolean isDeleted = hiringServices.deleteHiring(id);
        if (isDeleted) {
            String response = "Oder with ID " + id + " deleted successfully.";
            return ResponseEntity.ok(response);
        } else {
            String response = "Order with ID " + id + " does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);        }
    }

    @PostMapping("/edit-order-hiring-by-id/{Id}")
    public ResponseEntity<HiringModel> updateOrderById(@PathVariable String Id, @RequestBody HiringModel hiringModel) {
        Optional<HiringModel> result = hiringServices.editByHiringId(Id, hiringModel);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
