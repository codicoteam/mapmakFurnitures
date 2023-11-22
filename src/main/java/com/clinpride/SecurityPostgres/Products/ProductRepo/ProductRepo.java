package com.clinpride.SecurityPostgres.Products.ProductRepo;

import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import com.clinpride.SecurityPostgres.models.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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



}
