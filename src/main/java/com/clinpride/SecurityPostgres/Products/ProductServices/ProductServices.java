package com.clinpride.SecurityPostgres.Products.ProductServices;

import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public interface ProductServices {
    Optional<ProductsModel> editProduct(String id, ProductsModel product);
    boolean deleteProductByIds(List<String> packageIds);
    boolean deleteProduct(String id);
    ProductsModel createProduct(ProductsModel product);
    List<ProductsModel> getAllProduct();
    Optional<ProductsModel> getOneProduct(String id);
    List<ProductsModel> searchCategory(String categoryName);
    List<ProductsModel> searchColor(String color);
    List<ProductsModel> searchType(String type);
    List<ProductsModel> RangerProduct(double minPrice,double maxPrice);
    List<ProductsModel> searchByFeature(String feature);
    List<ProductsModel> RangerDiscount(double minPrice,double maxPrice);

}
