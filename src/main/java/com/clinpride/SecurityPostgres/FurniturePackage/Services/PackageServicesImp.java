package com.clinpride.SecurityPostgres.FurniturePackage.Services;

import com.clinpride.SecurityPostgres.FurniturePackage.Models.PackageModels;
import com.clinpride.SecurityPostgres.FurniturePackage.Repo.PackageRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PackageServicesImp implements PackageServices{
    private final PackageRepo packageRepo;

    @Override
    public Optional<PackageModels> editPackage(String id, PackageModels packageModels) {
        Optional<PackageModels> productOptional = packageRepo.findById(id);
        if (productOptional.isPresent()) {
            PackageModels packagess = productOptional.get();
            packagess.setPackageSalesPrice(packageModels.getPackageSalesPrice());
            packagess.setColors(packageModels.getColors());
            packagess.setPackageCategories(packageModels.getPackageCategories());
            packagess.setImages(packageModels.getImages());
            packagess.setPackageName(packageModels.getPackageName());
            packagess.setPackageLikes(packageModels.getPackageLikes());
            packagess.setStatus(packageModels.getStatus());
            packagess.setContent(packageModels.getContent());
            packagess.setImages(packageModels.getImages());
            packagess.setRegularPrice(packageModels.getRegularPrice());
            packagess.setPackageAttributes(packageModels.getPackageAttributes());
            packagess.setStatus(packageModels.getStatus());
            packagess.setReviews(packageModels.getReviews());
            packagess.setRatings(packageModels.getRatings());
            packagess.setContent(packageModels.getContent());
            packagess.setExplainRowMaterials(packageModels.getExplainRowMaterials());
            packagess.setPackageQuantity(packageModels.getPackageQuantity());
            packagess.setPackageWarranty(packageModels.getPackageWarranty());
            packagess.setExplainRowMaterials(packageModels.getExplainRowMaterials());
            packagess.setPackagePrice(packageModels.getPackagePrice());
            packagess.setRowMaterials(packageModels.getRowMaterials());
            PackageModels savedProduct = packageRepo.save(packagess);
            return Optional.of(savedProduct);
        }
        return Optional.empty();
    }

    @Override
    public boolean deletePackageByIds(List<String> packageIds) {
        packageRepo.deleteByIdIn(packageIds);
        return true;
    }

    @Override
    public boolean deletePackage(String id) {
        Optional<PackageModels> productOptional = packageRepo.findById(id);
        if (productOptional.isPresent()) {
            packageRepo.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public PackageModels createPackage(PackageModels packageModels) {
        return packageRepo.save(packageModels);
    }

    @Override
    public Page<PackageModels> getAllPackage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return packageRepo.findAll(pageable);
    }

    @Override
    public Optional<PackageModels> getOnePackage(String id) {
        return packageRepo.findById(id);
    }

    @Override
    public List<PackageModels> searchPackageCategory(String categoryName) {
        return packageRepo.findByPackageCategoriesCategoryName(categoryName);
    }

    @Override
    public List<PackageModels> searchPackageColor(String color) {
        return packageRepo.findByPackageAttributesColor(color);
    }

    @Override
    public List<PackageModels> searchPackageType(String type) {
        return packageRepo.findByPackageAttributesType(type);
    }

    @Override
    public List<PackageModels> RangerPackage(double minPrice, double maxPrice) {
        return packageRepo.findByPackagePriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<PackageModels> searchByFeature(String feature) {
        return packageRepo.findByPackageAttributesFeature(feature);
    }

    @Override
    public List<PackageModels> RangerDiscount(double minPrice, double maxPrice) {
        return packageRepo.findByPackageDiscountBetween(minPrice, maxPrice);
    }
}
