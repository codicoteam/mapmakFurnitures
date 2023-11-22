package com.clinpride.SecurityPostgres.OnDisplay.Controllers;

import com.clinpride.SecurityPostgres.OnDisplay.Models.OnDisplayModels;
import com.clinpride.SecurityPostgres.OnDisplay.Services.OnDisplayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product-on-display")
@RequiredArgsConstructor
public class OnDisplayControllers {
    private final OnDisplayService onDisplayService;
    @GetMapping("/getAll")
    public List<OnDisplayModels> getAllOnDisplay() {
        return onDisplayService.getAllOnDisplay();
    }

    @PostMapping("/edit-on-display/{id}")
    public ResponseEntity<OnDisplayModels> updateOnDisplay(@PathVariable String id, @RequestBody OnDisplayModels updatedProduct) {
        Optional<OnDisplayModels> updatedProductOptional = onDisplayService.editOnDisplay(id, updatedProduct);
        return updatedProductOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/delete-on-display/{id}")
    public ResponseEntity<String> deleteOnDisplayById(@PathVariable String id) {
        boolean isDeleted = onDisplayService.deleteOnDisplay(id);
        if (isDeleted) {
            String response = "Product with ID " + id + " deleted successfully.";
            return ResponseEntity.ok(response);
        } else {
            String response = "Product with ID " + id + " does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);        }
    }

    @GetMapping("/find-on-display/{id}")
    public ResponseEntity<OnDisplayModels> getOnDisplayById(@PathVariable String id) {
        Optional<OnDisplayModels> product = onDisplayService.getOneOnDisplay(id);
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create-on-display")
    public ResponseEntity<OnDisplayModels> createOnDisplay(@RequestBody OnDisplayModels oDisplayModels) {
        OnDisplayModels savedProduct = onDisplayService.createOnDisplay(oDisplayModels);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }



}
