package com.clinpride.SecurityPostgres.FurniturePackage.Controllers;

import com.clinpride.SecurityPostgres.FurniturePackage.Models.PackageModels;
import com.clinpride.SecurityPostgres.FurniturePackage.Services.PackageServices;
import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/package")

public class ControllerPackage {
    private final PackageServices packageServices;



    @PostMapping("/create-package")
    public ResponseEntity<PackageModels> createProduct(@RequestBody PackageModels PackageModels) {
        PackageModels savedPackage = packageServices.createPackage(PackageModels);
        return new ResponseEntity<>(savedPackage, HttpStatus.CREATED);
    }

    @GetMapping("/getall-package")
    public Page<PackageModels> getAllProducts(@RequestParam int page, int size) {
        return packageServices.getAllPackage(page, size);
    }


    @GetMapping("/find-package/{id}")
    public ResponseEntity<PackageModels> getProductById(@PathVariable String id) {
        Optional<PackageModels> product = packageServices.getOnePackage(id);
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/delete-package/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable String id) {
        boolean isDeleted = packageServices.deletePackage(id);
        if (isDeleted) {
            String response = "Package with ID " + id + " deleted successfully.";
            return ResponseEntity.ok(response);
        } else {
            String response = "Package with ID " + id + " does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);        }
    }
    @DeleteMapping("/delete-packages")
    public ResponseEntity<String> deletePackages(@RequestBody List<String> packageIds) {
        packageServices.deletePackageByIds(packageIds);
        return ResponseEntity.ok("Package deleted successfully");
    }

    @PostMapping("/edit-package/{id}")
    public ResponseEntity<PackageModels> updateProduct(@PathVariable String id, @RequestBody PackageModels updatedPackageModels) {
        Optional<PackageModels> updatedProductOptional = packageServices.editPackage(id, updatedPackageModels);
        return updatedProductOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryName}")
    public List<PackageModels> getProductsByCategoryName(@PathVariable String categoryName) {
        return packageServices.searchPackageCategory(categoryName);
    }

    @GetMapping("/color/{color}")
    public List<PackageModels> getProductsByColor(@PathVariable String color) {
        return packageServices.searchPackageColor(color);
    }

    @GetMapping("/type/{type}")
    public List<PackageModels> getProductsByType(@PathVariable String type) {
        return packageServices.searchPackageType(type);
    }


    @GetMapping("/getproductby/pricerange")
    public List<PackageModels> getProductsByPriceRange(@RequestParam("minPrice") double minPrice,
                                                       @RequestParam("maxPrice") double maxPrice) {
        return packageServices.RangerPackage(minPrice, maxPrice);
    }
    @GetMapping("/search/by/feature")
    public List<PackageModels> getProductsByFeature(@RequestParam("feature") String feature) {
        return packageServices.searchByFeature(feature);
    }

    @GetMapping("/get/product/by/package/discount")
    public List<PackageModels> getProductsByDiscountRange(@RequestParam("minDiscount") double minDiscount,
                                                          @RequestParam("maxDiscount") double maxDiscount) {
        return packageServices.RangerDiscount(minDiscount, maxDiscount);
    }


}
