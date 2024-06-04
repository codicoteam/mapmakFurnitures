package com.clinpride.SecurityPostgres.Promotions.Controllers;
import com.clinpride.SecurityPostgres.Promotions.Models.PromotionModel;
import com.clinpride.SecurityPostgres.Promotions.Services.PromotionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RestController
@RequestMapping("/api/v1/product-promotion")
@RequiredArgsConstructor

public class promotioncontroller {

    private final PromotionServiceImpl promotionService;
    @GetMapping("/getAll")
    public List<PromotionModel> getAllPromotion() {
        return promotionService.getAllProduct();
    }

    @PostMapping("/edit-promotion-product/{id}")
    public ResponseEntity<PromotionModel> updateProduct(@PathVariable String id, @RequestBody PromotionModel updatedProduct) {
        Optional<PromotionModel> updatedProductOptional = Optional.ofNullable(promotionService.editProduct(id, updatedProduct));
        return updatedProductOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/delete-promotion/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable String id) {
        boolean isDeleted = promotionService.deleteProduct(id);
        if (isDeleted) {
            String response = "Product with ID " + id + " deleted successfully.";
            return ResponseEntity.ok(response);
        } else {
            String response = "Product with ID " + id + " does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);        }
    }

    @GetMapping("/find-promotion/{id}")
    public ResponseEntity<PromotionModel> getProductById(@PathVariable String id) {
        Optional<PromotionModel> product = promotionService.getOneProduct(id);
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create-promotion")
    public ResponseEntity<PromotionModel> createProduct(@RequestBody PromotionModel promotionModel) {
        PromotionModel savedProduct = promotionService.createProduct(promotionModel);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }


}
