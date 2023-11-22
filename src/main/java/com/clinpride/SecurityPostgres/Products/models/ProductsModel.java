package com.clinpride.SecurityPostgres.Products.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document(collection = "products")

public class ProductsModel {
    @Id
    private String id;
    private String productSalesPrice;
    private Boolean productPurchasable;
    private Boolean showProduct;
    private int productQuantity;
    private double productPrice;//new
    private double productDiscount; // new
    private String setState;
    private String content;// new
    private String status;
    private String date;
    private String productLocation;
    private String regularPrice;
    private String productName;
    private String productDescription;
    private int productLikes;
    private int initialOrder;
    private List<Category> productCategories;
    private List<String> colors;
    private List<Image> images;
    private List<productAttr> productAttributes;
    private List<review> reviews;
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
    @Data
    public static class review {
        private Boolean showReview;
        private String username;
        private String reviewReason;
        private String color;
        private Integer ratings;
        private String dateNow;
        public review() {
            this.dateNow = LocalDate.now().toString();
        }
    }
}
