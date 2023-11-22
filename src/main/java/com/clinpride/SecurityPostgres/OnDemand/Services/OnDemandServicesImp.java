package com.clinpride.SecurityPostgres.OnDemand.Services;

import com.clinpride.SecurityPostgres.OnDemand.Models.OnDemandModel;
import com.clinpride.SecurityPostgres.OnDemand.Repo.OnDemandRepo;
import com.clinpride.SecurityPostgres.Products.ProductControllers.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor

public class OnDemandServicesImp implements OnDemandServices{
    private final OnDemandRepo onDemandRepo;
    @Override
    public OnDemandModel editProduct(String id, OnDemandModel onDemandModel) {

        OnDemandModel existingPromotion = onDemandRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("OnDemand not found with id: " + id));

        existingPromotion.setProductSalesPrice(onDemandModel.getProductSalesPrice());
        existingPromotion.setProductPurchasable(onDemandModel.getProductPurchasable());
        existingPromotion.setShowProduct(onDemandModel.getShowProduct());
        existingPromotion.setProductQuantity(onDemandModel.getProductQuantity());
        existingPromotion.setProductPrice(onDemandModel.getProductPrice());
        existingPromotion.setProductDiscount(onDemandModel.getProductDiscount());
        existingPromotion.setSetState(onDemandModel.getSetState());
        existingPromotion.setStatus(onDemandModel.getStatus());
        existingPromotion.setDate(onDemandModel.getDate());
        existingPromotion.setProductLocation(onDemandModel.getProductLocation());
        existingPromotion.setRegularPrice(onDemandModel.getRegularPrice());
        existingPromotion.setProductName(onDemandModel.getProductName());
        existingPromotion.setProductDescription(onDemandModel.getProductDescription());
        existingPromotion.setProductLikes(onDemandModel.getProductLikes());
        existingPromotion.setInitialOrder(onDemandModel.getInitialOrder());
        existingPromotion.setProductCategories(onDemandModel.getProductCategories());
        existingPromotion.setImages(onDemandModel.getImages());
        existingPromotion.setProductAttributes(onDemandModel.getProductAttributes());
        existingPromotion.setProductGuarantee(onDemandModel.getProductGuarantee());
        existingPromotion.setContent(onDemandModel.getContent());
        existingPromotion.setWholesalersName(onDemandModel.getWholesalersName());
        existingPromotion.setWholesalerProductPrice(onDemandModel.getWholesalerProductPrice());
        existingPromotion.setWholesalersEmail(onDemandModel.getWholesalersEmail());
        existingPromotion.setWholesalersPhone(onDemandModel.getWholesalersPhone());
        return onDemandRepo.save(existingPromotion);
    }

    @Override
    public boolean deleteProduct(String id) {

        Optional<OnDemandModel> productOptional = onDemandRepo.findById(id);
        if (productOptional.isPresent()) {
            onDemandRepo.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public OnDemandModel createProduct(OnDemandModel onDemandModel) {
        return onDemandRepo.save(onDemandModel);
    }

    @Override
    public List<OnDemandModel> getAllProduct() {
        return onDemandRepo.findAll();
    }

    @Override
    public Optional<OnDemandModel> getOneProduct(String id) {
        return onDemandRepo.findById(id);
    }
}
