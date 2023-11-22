package com.clinpride.SecurityPostgres.FurniturePackage.Services;

import com.clinpride.SecurityPostgres.FurniturePackage.Models.PackageModels;
import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface PackageServices {
    Optional<PackageModels> editPackage(String id, PackageModels packageModels);
    boolean deletePackageByIds(List<String> packageIds);
    boolean deletePackage(String id);
    PackageModels createPackage(PackageModels packageModels);
    List<PackageModels> getAllPackage();
    Optional<PackageModels> getOnePackage(String id);
    List<PackageModels> searchPackageCategory(String categoryName);
    List<PackageModels> searchPackageColor(String color);
    List<PackageModels> searchPackageType(String type);
    List<PackageModels> RangerPackage(double minPrice,double maxPrice);
    List<PackageModels> searchByFeature(String feature);
    List<PackageModels> RangerDiscount(double minPrice,double maxPrice);
}
