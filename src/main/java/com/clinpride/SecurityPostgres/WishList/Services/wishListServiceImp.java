package com.clinpride.SecurityPostgres.WishList.Services;

import com.clinpride.SecurityPostgres.WishList.wishListModel.WishListModel;
import com.clinpride.SecurityPostgres.WishList.wishListRep.WishListRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class wishListServiceImp  implements  wishListServices{
    final private WishListRepo wishListRepo;
    @Override
    public String createWishList(WishListModel orderObject) {
        try {
            wishListRepo.save(orderObject);
            return "Wish List created successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Wish List saving failed";
        }
    }

    @Override
    public WishListModel getOneWishListById(String id) {
        return wishListRepo.findById(id).orElse(null);
    }

    @Override
    public List<WishListModel> getWishListByCustomerEmail(String Email) {
        return wishListRepo.findByCustomerEmail(Email);
    }



    @Override
    public Optional<WishListModel> editByWishListId(String Id, WishListModel wishListModel) {
        Optional<WishListModel> existingWishList = wishListRepo.findById(Id);
        if (existingWishList.isPresent()) {
            WishListModel wishListModel1 = existingWishList.get();
            // Update the fields of the existing order with the updatedOrder values
            wishListModel1.setProducts(wishListModel.getProducts());
            wishListModel1.setWishListTotal(wishListModel.getWishListTotal());
            wishListModel1.setCustomerEmail(wishListModel.getCustomerEmail());
            wishListModel1.setCustomerPhoneNumber(wishListModel.getCustomerPhoneNumber());
            wishListModel1.setWishListTotalMoney(wishListModel.getWishListTotalMoney());
            wishListModel1.setShowWishList(wishListModel.getShowWishList());
            wishListModel1.setBooleanWishList(wishListModel.getBooleanWishList());
            wishListModel1.setStatus(wishListModel.getStatus());
            wishListModel1.setComment(wishListModel.getComment());

            return Optional.of(wishListRepo.save(wishListModel1));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteWishList(String id) {
        Optional<WishListModel> productOptional = wishListRepo.findById(id);
        if (productOptional.isPresent()) {
            wishListRepo.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }


}
