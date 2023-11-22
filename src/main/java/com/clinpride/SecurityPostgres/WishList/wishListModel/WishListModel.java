package com.clinpride.SecurityPostgres.WishList.wishListModel;

import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "wishList")
public class WishListModel {
    @Id
    private String id;
    private  String wishListName;
    String wishListId = RandomStringUtils.randomAlphanumeric(6);
    private List<ProductsModel> products;
    private String wishListTotal;
    private String customerEmail;
    private String customerPhoneNumber;
    private double wishListTotalMoney;
    private Boolean showWishList;
    private Boolean booleanWishList;
    private String status;
    private String comment;
    private String dateNow;
    private String orderTime;
    public WishListModel() {
        this.dateNow = LocalDate.now().toString();
        this.orderTime = LocalTime.now().toString(); // Set the order time to the current time
    }

}
