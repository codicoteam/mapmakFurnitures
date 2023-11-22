package com.clinpride.SecurityPostgres.FurniturePackage.Repo;

import com.clinpride.SecurityPostgres.FurniturePackage.Models.PackageModels;
import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackageRepo extends MongoRepository<PackageModels, String> {
    Optional<PackageModels> findById(String id);
    void deleteByIdIn(List<String> productIds);
    List<PackageModels> findByPackageCategoriesCategoryName(String categoryName);
    List<PackageModels> findByPackageAttributesColor(String color);
    List<PackageModels> findByPackageAttributesType(String type);
    List<PackageModels> findByPackagePriceBetween(double minPrice, double maxPrice);
    List<PackageModels> findByPackageAttributesFeature(String feature);
    List<PackageModels> findByPackageDiscountBetween(double minDiscount, double maxDiscount);

}
