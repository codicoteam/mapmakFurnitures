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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
@RestController
@RequestMapping("/api/v1/products")

public class Controllers {
    private final ProductServices productService;

    @Autowired
    public Controllers(ProductServices productService) {
        this.productService = productService;
    }

    @PostMapping("/create-product")
    public ResponseEntity<ProductsModel> createProduct(@RequestBody ProductsModel product) {
        ProductsModel savedProduct = productService.createProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/getall-products")
    public Page<ProductsModel> getAllProducts(@RequestParam int page, int size) {
        return productService.getAllProduct(page, size);
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


    @GetMapping("/category")
    public Page<ProductsModel> getProductsByCategoryName(@RequestParam int page, int size, String categoryName) {
        return productService.searchCategory(categoryName, page, size);
    }



    @GetMapping("/color")
    public Page<ProductsModel>  getProductsByColor(@RequestParam int page, int size, String color) {
        return productService.searchColor(color, page, size);
    }

    @GetMapping("/type")
    public Page<ProductsModel> getProductsByType(@RequestParam int page, int size, String type) {
        return productService.searchType(type, page , size);
    }


    @GetMapping("/getproductby/pricerange")
    public Page<ProductsModel> getProductsByPriceRange(@RequestParam("minPrice") double minPrice,
                                                       @RequestParam("maxPrice") double maxPrice,  @RequestParam int page, int size) {
        return productService.RangerProduct(minPrice, maxPrice, page, size);
    }


    @GetMapping("/search/by/feature")
    public Page<ProductsModel> getProductsByFeature(@RequestParam("feature") String feature, @RequestParam int page, int size) {
        return productService.searchByFeature(feature, page , size);
    }

    @GetMapping("/get/product/by/product/discount")
    public Page<ProductsModel> getProductsByDiscountRange(@RequestParam("minDiscount") double minDiscount,
                                                          @RequestParam("maxDiscount") double maxDiscount,
                                                          @RequestParam int page, int size) {
        return productService.RangerDiscount(minDiscount, maxDiscount, page, size);
    }


    @GetMapping("/search/by/productname")
    public Page<ProductsModel> getAllProductByName(@RequestParam("productName") String productName, @RequestParam int page, int size) {
        return productService.getAllProductByName(productName, page , size);
    }

}
