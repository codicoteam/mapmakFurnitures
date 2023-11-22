package com.clinpride.SecurityPostgres.WishList.wishListControllers;

import com.clinpride.SecurityPostgres.Orders.models.OrderModels;
import com.clinpride.SecurityPostgres.Orders.services.OrderServices;
import com.clinpride.SecurityPostgres.WishList.Services.wishListServices;
import com.clinpride.SecurityPostgres.WishList.wishListModel.WishListModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/add-to-wishlist")
@RequiredArgsConstructor
public class wishListControllers {
    private final wishListServices wishListServices;


    @PostMapping("/createWishList")
    public String saveWishList(@RequestBody WishListModel WishListModel) {
        return wishListServices.createWishList(WishListModel);
    }
    @GetMapping("/find-wishlist/{id}")
    public ResponseEntity<WishListModel> getOrderById(@PathVariable String id) {
        WishListModel wishListModel = wishListServices.getOneWishListById(id);
        if (wishListModel != null) {
            return ResponseEntity.ok(wishListModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/find-wishlist-customer-email")
    public ResponseEntity<List<WishListModel>> getWishListByCustomerEmail(@RequestParam String email) {
        List<WishListModel> orders = wishListServices.getWishListByCustomerEmail(email);
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





    @PostMapping("/delete-wishlist-by-id/{id}")
    public ResponseEntity<String> deleteWishList(@PathVariable String id) {
        boolean isDeleted = wishListServices.deleteWishList(id);
        if (isDeleted) {
            String response = "deleted successfully.";
            return ResponseEntity.ok(response);
        } else {
            String response = "does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);        }
    }

    @PostMapping("/edit-wishlist-by-id/{Id}")
    public ResponseEntity<WishListModel> updateOrderById(@PathVariable String Id, @RequestBody WishListModel updatedOrder) {
        Optional<WishListModel> result = wishListServices.editByWishListId(Id, updatedOrder);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
