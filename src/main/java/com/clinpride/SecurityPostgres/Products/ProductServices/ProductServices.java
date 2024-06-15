package com.clinpride.SecurityPostgres.Products.ProductServices;

import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.util.List;
import java.util.Optional;

@Service

public interface ProductServices {
    Optional<ProductsModel> editProduct(String id, ProductsModel product);
    boolean deleteProductByIds(List<String> packageIds);
    boolean deleteProduct(String id);
    ProductsModel createProduct(ProductsModel product);
    Page<ProductsModel> getAllProduct(int page, int size);
    Optional<ProductsModel> getOneProduct(String id);
    Page<ProductsModel> searchCategory(String categoryName, int page, int size);
    Page<ProductsModel> searchColor(String color,int page, int size);
    Page<ProductsModel> searchType(String type, int page, int size);
    Page<ProductsModel> RangerProduct(double minPrice,double maxPrice, int page, int size);
    Page<ProductsModel> searchByFeature(String feature, int page, int size);
    Page<ProductsModel> RangerDiscount(double minPrice,double maxPrice, int page, int size);
    Page<ProductsModel> getAllProductByName(String ProductName, int page, int size);

}
