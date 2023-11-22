package com.clinpride.SecurityPostgres.Promotions.Models;

import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document(collection = "promotions")
public class PromotionModel {
    @Id
    private String id;
    private String productSalesPrice;
    private Boolean productPurchasable;
    private Boolean showProduct;
    private int productQuantity;
    private double productPrice;//new
    private double productDiscount; // new
    private String setState; // new
    private String status;
    private String content;// new
    private String date;
    private String productLocation;
    private String regularPrice;
    private String productName;
    private String productDescription;
    private int productLikes;
    private int initialOrder;
    private List<ProductsModel.Category> productCategories;
    private List<ProductsModel.Image> images;
    private List<ProductsModel.productAttr> productAttributes;

    private String productGuarantee;

    private String wholesalersName;
    private String wholesalerProductPrice;

    private String wholesalersEmail;
    private String wholesalersPhone;

    // Constructors, getters, and setters
    @Data
    public static class Category {
        private String categoryName;
        private String imageUrl;
    }

    @Data
    public static class Image {
        private String imageUrl;
    }
    @Data
    public static class productAttr {
        private String capacity; //new
        private String color;
        private String type;
        private String feature;
    }
}
