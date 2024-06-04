package com.clinpride.SecurityPostgres.Products.ProductControllers;

import com.clinpride.SecurityPostgres.Products.ProductServices.ProductServices;
import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import com.clinpride.SecurityPostgres.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5000" ,allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/products")

public class Controllers {
    private final ProductServices productService;

    @Autowired
    public Controllers(ProductServices productService) {
        this.productService = productService;
    }

    @CrossOrigin(origins = "http://localhost:5000" ,allowCredentials = "true")
    @PostMapping("/create-product")
    public ResponseEntity<ProductsModel> createProduct(@RequestBody ProductsModel product) {
        ProductsModel savedProduct = productService.createProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/getall-products")
    public List<ProductsModel> getAllProducts() {
        return productService.getAllProduct();
    }

    @GetMapping("find-product/{id}")
    public ResponseEntity<ProductsModel> getProductById(@PathVariable String id) {
        Optional<ProductsModel> product = productService.getOneProduct(id);
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("delete-product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable String id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            String response = "Product with ID " + id + " deleted successfully.";
            return ResponseEntity.ok(response);
        } else {
            String response = "Product with ID " + id + " does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);        }
    }
    @DeleteMapping("/delete-products")
    public ResponseEntity<String> deletePackages(@RequestBody List<String> packageIds) {
        productService.deleteProductByIds(packageIds);
        return ResponseEntity.ok("Products deleted successfully");
    }

    @PostMapping("edit-product/{id}")
    public ResponseEntity<ProductsModel> updateProduct(@PathVariable String id, @RequestBody ProductsModel updatedProduct) {
        Optional<ProductsModel> updatedProductOptional = productService.editProduct(id, updatedProduct);
        return updatedProductOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryName}")
    public List<ProductsModel> getProductsByCategoryName(@PathVariable String categoryName) {
        return productService.searchCategory(categoryName);
    }

    @GetMapping("/color/{color}")
    public List<ProductsModel> getProductsByColor(@PathVariable String color) {
        return productService.searchColor(color);
    }

    @GetMapping("/type/{type}")
    public List<ProductsModel> getProductsByType(@PathVariable String type) {
        return productService.searchType(type);
    }


    @GetMapping("/getproductby/pricerange")
    public List<ProductsModel> getProductsByPriceRange(@RequestParam("minPrice") double minPrice,
                                                       @RequestParam("maxPrice") double maxPrice) {
        return productService.RangerProduct(minPrice, maxPrice);
    }
    @GetMapping("/search/by/feature")
    public List<ProductsModel> getProductsByFeature(@RequestParam("feature") String feature) {
        return productService.searchByFeature(feature);
    }

    @GetMapping("/get/product/by/product/discount")
    public List<ProductsModel> getProductsByDiscountRange(@RequestParam("minDiscount") double minDiscount,
                                                          @RequestParam("maxDiscount") double maxDiscount) {
        return productService.RangerDiscount(minDiscount, maxDiscount);
    }

}
