package com.clinpride.SecurityPostgres.Products.ProductRepo;

import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import com.clinpride.SecurityPostgres.models.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ProductRepo extends MongoRepository<ProductsModel, String> {
    Optional<ProductsModel> findById(String id);
    void deleteByIdIn(List<String> productIds);

    List<ProductsModel> findByProductCategoriesCategoryName(String categoryName);
    List<ProductsModel> findByProductAttributesColor(String color);
    List<ProductsModel> findByProductAttributesType(String type);
    List<ProductsModel> findByProductPriceBetween(double minPrice, double maxPrice);
    List<ProductsModel> findByProductAttributesFeature(String feature);
    List<ProductsModel> findByProductDiscountBetween(double minDiscount, double maxDiscount);
    Page<ProductsModel> findByProductCategoriesCategoryName(String categoryName, Pageable pageable);
    Page<ProductsModel>findByProductAttributesColor(String color,  Pageable pageable);
    Page<ProductsModel> findByProductAttributesType(String type,  Pageable pageable);
    Page<ProductsModel>findByProductPriceBetween(double minPrice, double maxPrice,  Pageable pageable);
    Page<ProductsModel> findByProductAttributesFeature(String feature,  Pageable pageable);
    Page<ProductsModel>findByProductDiscountBetween(double minDiscount, double maxDiscount,  Pageable pageable);
    Page<ProductsModel> findByProductName(String productName, Pageable pageable);
    List<ProductsModel> findByProductName(String productName);




}
