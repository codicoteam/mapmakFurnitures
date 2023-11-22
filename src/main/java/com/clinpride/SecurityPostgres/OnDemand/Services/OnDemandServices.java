package com.clinpride.SecurityPostgres.OnDemand.Services;

import com.clinpride.SecurityPostgres.OnDemand.Models.OnDemandModel;
import com.clinpride.SecurityPostgres.Promotions.Models.PromotionModel;

import java.util.List;
import java.util.Optional;

public interface OnDemandServices {
    OnDemandModel editProduct(String id, OnDemandModel onDemandModel);
    boolean deleteProduct(String id);
    OnDemandModel createProduct(OnDemandModel onDemandModel);
    List<OnDemandModel> getAllProduct();
    Optional<OnDemandModel> getOneProduct(String id);
}
