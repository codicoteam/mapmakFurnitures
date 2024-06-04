package com.clinpride.SecurityPostgres.OnDemand.Controllers;


import com.clinpride.SecurityPostgres.OnDemand.Models.OnDemandModel;
import com.clinpride.SecurityPostgres.OnDemand.Services.OnDemandServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RestController
@RequestMapping("/api/v1/product-ondemand")
@RequiredArgsConstructor
public class OnDemandControllers {
    private final OnDemandServices onDemandServices;
    @GetMapping("/getAll/ondemand")
    public List<OnDemandModel> getAllPromotion() {
        return onDemandServices.getAllProduct();
    }

    @PostMapping("/edit-ondemand-product/{id}")
    public ResponseEntity<OnDemandModel> updateProduct(@PathVariable String id, @RequestBody OnDemandModel onDemandModel) {
        Optional<OnDemandModel> updatedProductOptional = Optional.ofNullable(onDemandServices.editProduct(id, onDemandModel));
        return updatedProductOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/delete-promotion/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable String id) {
        boolean isDeleted = onDemandServices.deleteProduct(id);
        if (isDeleted) {
            String response = "Product with ID " + id + " deleted successfully.";
            return ResponseEntity.ok(response);
        } else {
            String response = "Product with ID " + id + " does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);        }
    }

    @GetMapping("/find-ondemand/{id}")
    public ResponseEntity<OnDemandModel> getProductById(@PathVariable String id) {
        Optional<OnDemandModel> product = onDemandServices.getOneProduct(id);
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create-ondemand")
    public ResponseEntity<OnDemandModel> createProduct(@RequestBody OnDemandModel onDemandModel) {
        OnDemandModel savedProduct = onDemandServices.createProduct(onDemandModel);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
}
