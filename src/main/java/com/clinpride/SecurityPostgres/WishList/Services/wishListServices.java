package com.clinpride.SecurityPostgres.WishList.Services;

import com.clinpride.SecurityPostgres.WishList.wishListModel.WishListModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface wishListServices {

    String createWishList(WishListModel orderObject);
    WishListModel getOneWishListById(String id);
    List<WishListModel> getWishListByCustomerEmail(String Email);
    Optional<WishListModel> editByWishListId(String Id, WishListModel wishListModel);
    boolean deleteWishList (String id);
}
