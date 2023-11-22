package com.clinpride.SecurityPostgres.Promotions.Services;

import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import com.clinpride.SecurityPostgres.Promotions.Models.PromotionModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface PromotionServiceImpl {
    PromotionModel editProduct(String id, PromotionModel promotionModel);
    boolean deleteProduct(String id);
    PromotionModel createProduct(PromotionModel promotionModel);
    List<PromotionModel> getAllProduct();
    Optional<PromotionModel> getOneProduct(String id);
}
