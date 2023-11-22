package com.clinpride.SecurityPostgres.WishList.wishListRep;

import com.clinpride.SecurityPostgres.Orders.models.OrderModels;
import com.clinpride.SecurityPostgres.WishList.wishListModel.WishListModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface WishListRepo extends MongoRepository<WishListModel, String> {
    List<WishListModel> findByCustomerEmail(String email);
    Optional<WishListModel> findByWishListId(String orderId);
}
