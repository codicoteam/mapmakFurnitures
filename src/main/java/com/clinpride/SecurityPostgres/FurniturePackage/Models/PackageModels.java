package com.clinpride.SecurityPostgres.FurniturePackage.Models;

import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@Builder
@Document(collection = "Package")

public class PackageModels {
    @Id
    private String id;
    private String packageSalesPrice;
    private String packageRecommendation;
    private Boolean packagePurchasable;
    private Boolean showPackage;
    private int packageQuantity;
    private double packagePrice;//new
    private double packageDiscount; // new
    private String content;// new
    private String status;
    private String dateNow;
    private String regularPrice;
    private String packageName;
    private String packageDescription;
    private int packageLikes;
    private int ratings;
    int initialOrder = 1;
    private List<String> rowMaterials;
    private String explainRowMaterials;
    private List<ProductsModel.Category> packageCategories;
    private List<String> colors;
    private List<PackageModels.Image> images;
    private List<PackageModels.packageAttr> packageAttributes;
    private List<PackageModels.review> reviews;
    private String packageWarranty;
    public PackageModels() {
        this.dateNow = LocalDate.now().toString();
    }
    // Constructors, getters, and setters
    @Data
    public static class Category {
        private String categoryName;
        private String imageUrl;
    }
    @Data
    public static class Image {
        private String imageUrl;
        private String productName;
        private String productPrice;
        private String wholesaleEmail;
        private String wholesaleName;
        private String wholesalePhone;
    }
    @Data
    public static class packageAttr {
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

        public Boolean getShowReview() {
            return showReview;
        }

        public void setShowReview(Boolean showReview) {
            this.showReview = showReview;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getReviewReason() {
            return reviewReason;
        }

        public void setReviewReason(String reviewReason) {
            this.reviewReason = reviewReason;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Integer getRatings() {
            return ratings;
        }

        public void setRatings(Integer ratings) {
            this.ratings = ratings;
        }

        public String getDateNow() {
            return dateNow;
        }

        public void setDateNow(String dateNow) {
            this.dateNow = dateNow;
        }

        public review() {
            this.dateNow = LocalDate.now().toString();
        }
    }
}
