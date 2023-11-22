package com.clinpride.SecurityPostgres.Promotions.Services;

import com.clinpride.SecurityPostgres.Products.ProductControllers.NotFoundException;
import com.clinpride.SecurityPostgres.Promotions.Models.PromotionModel;
import com.clinpride.SecurityPostgres.Promotions.Repo.PromotionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class promotionService implements PromotionServiceImpl{
    private final PromotionRepo promotionRepo;

    @Override
    public PromotionModel editProduct(String id, PromotionModel promotionModel) {
        PromotionModel existingPromotion = promotionRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Promotion not found with id: " + id));

        existingPromotion.setProductSalesPrice(promotionModel.getProductSalesPrice());
        existingPromotion.setProductPurchasable(promotionModel.getProductPurchasable());
        existingPromotion.setShowProduct(promotionModel.getShowProduct());
        existingPromotion.setProductQuantity(promotionModel.getProductQuantity());
        existingPromotion.setProductPrice(promotionModel.getProductPrice());
        existingPromotion.setProductDiscount(promotionModel.getProductDiscount());
        existingPromotion.setSetState(promotionModel.getSetState());
        existingPromotion.setStatus(promotionModel.getStatus());
        existingPromotion.setDate(promotionModel.getDate());
        existingPromotion.setProductLocation(promotionModel.getProductLocation());
        existingPromotion.setRegularPrice(promotionModel.getRegularPrice());
        existingPromotion.setProductName(promotionModel.getProductName());
        existingPromotion.setProductDescription(promotionModel.getProductDescription());
        existingPromotion.setProductLikes(promotionModel.getProductLikes());
        existingPromotion.setInitialOrder(promotionModel.getInitialOrder());
        existingPromotion.setProductCategories(promotionModel.getProductCategories());
        existingPromotion.setImages(promotionModel.getImages());
        existingPromotion.setProductAttributes(promotionModel.getProductAttributes());
        existingPromotion.setProductGuarantee(promotionModel.getProductGuarantee());
        existingPromotion.setContent(promotionModel.getContent());
        existingPromotion.setWholesalersName(promotionModel.getWholesalersName());
        existingPromotion.setWholesalerProductPrice(promotionModel.getWholesalerProductPrice());
        existingPromotion.setWholesalersEmail(promotionModel.getWholesalersEmail());
        existingPromotion.setWholesalersPhone(promotionModel.getWholesalersPhone());
        return promotionRepo.save(existingPromotion);
    }

    @Override
    public boolean deleteProduct(String id) {
        Optional<PromotionModel> productOptional = promotionRepo.findById(id);
        if (productOptional.isPresent()) {
            promotionRepo.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public PromotionModel createProduct(PromotionModel promotionModel) {
        return promotionRepo.save(promotionModel);
    }

    @Override
    public List<PromotionModel> getAllProduct() {
        return promotionRepo.findAll();
    }

    @Override
    public Optional<PromotionModel> getOneProduct(String id) {
        return promotionRepo.findById(id);
    }
}
