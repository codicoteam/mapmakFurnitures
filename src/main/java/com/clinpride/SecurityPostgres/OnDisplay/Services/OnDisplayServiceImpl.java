package com.clinpride.SecurityPostgres.OnDisplay.Services;

import com.clinpride.SecurityPostgres.OnDisplay.Models.OnDisplayModels;
import com.clinpride.SecurityPostgres.OnDisplay.Repo.OnDisplayRepo;
import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class OnDisplayServiceImpl implements OnDisplayService  {
    private final OnDisplayRepo onDisplayRepo;
    @Override
    public Optional<OnDisplayModels> editOnDisplay(String id, OnDisplayModels opnDisplayModels) {
        Optional<OnDisplayModels> productOptional = onDisplayRepo.findById(id);
        if (productOptional.isPresent()) {
            OnDisplayModels product = productOptional.get();
            product.setDescription(opnDisplayModels.getDescription());
            product.setImage(opnDisplayModels.getImage());
            product.setImage(opnDisplayModels.getImage());
            product.setId(opnDisplayModels.getTitle());

            OnDisplayModels savedProduct = onDisplayRepo.save(product);

            return Optional.of(savedProduct);
        }
        return Optional.empty();
    }
    @Override
    public boolean deleteOnDisplay(String id) {
        Optional<OnDisplayModels> productOptional = onDisplayRepo.findById(id);
        if (productOptional.isPresent()) {
            onDisplayRepo.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public OnDisplayModels createOnDisplay(OnDisplayModels onDisplayModels) {
        return onDisplayRepo.save(onDisplayModels);
    }

    @Override
    public List<OnDisplayModels> getAllOnDisplay() {
        return onDisplayRepo.findAll();
    }

    @Override
    public Optional<OnDisplayModels> getOneOnDisplay(String id) {
        return onDisplayRepo.findById(id);
    }
}
