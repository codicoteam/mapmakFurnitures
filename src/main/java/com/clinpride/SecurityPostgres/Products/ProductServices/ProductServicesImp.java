package com.clinpride.SecurityPostgres.Products.ProductServices;

import com.clinpride.SecurityPostgres.Products.ProductControllers.NotFoundException;
import com.clinpride.SecurityPostgres.Products.ProductRepo.ProductRepo;
import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServicesImp implements ProductServices{
    private final ProductRepo productRepo;

    @Override
    public Optional<ProductsModel> editProduct(String id, ProductsModel updatedProduct) {
        Optional<ProductsModel> productOptional = productRepo.findById(id);
        if (productOptional.isPresent()) {
            ProductsModel product = productOptional.get();
            product.setProductAttributes(updatedProduct.getProductAttributes());
            product.setColors(updatedProduct.getColors());
            product.setProductCategories(updatedProduct.getProductCategories());
            product.setProductDescription(updatedProduct.getProductDescription());
            product.setProductGuarantee(updatedProduct.getProductGuarantee());
            product.setProductLocation(updatedProduct.getProductLocation());
            product.setProductName(updatedProduct.getProductName());
            product.setContent(updatedProduct.getContent());
            product.setImages(updatedProduct.getImages());
            product.setRegularPrice(updatedProduct.getRegularPrice());
            product.setProductPurchasable(updatedProduct.getProductPurchasable());
            product.setStatus(updatedProduct.getStatus());
            product.setProductSalesPrice(updatedProduct.getProductSalesPrice());
            product.setProductQuantity(updatedProduct.getProductQuantity());
            product.setShowProduct(updatedProduct.getShowProduct());
            product.setWholesalerProductPrice(updatedProduct.getWholesalerProductPrice());
            product.setWholesalersEmail(updatedProduct.getWholesalersEmail());
            product.setWholesalersName(updatedProduct.getWholesalersName());
            product.setWholesalersPhone(updatedProduct.getWholesalersPhone());
            product.setReviews(updatedProduct.getReviews());
            ProductsModel savedProduct = productRepo.save(product);

            return Optional.of(savedProduct);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteProductByIds(List<String> packageIds) {
        productRepo.deleteByIdIn(packageIds);
        return true;
    }

    @Override
    public boolean deleteProduct(String id) {
        Optional<ProductsModel> productOptional = productRepo.findById(id);
        if (productOptional.isPresent()) {
            productRepo.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public ProductsModel createProduct(ProductsModel product) {
        System.out.println(product);
        return productRepo.save(product);
    }

    @Override
    public List<ProductsModel> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Optional<ProductsModel> getOneProduct(String id) {
        return productRepo.findById(id);
    }

    @Override
    public List<ProductsModel> searchCategory(String categoryName) {
        List<ProductsModel> products = productRepo.findByProductCategoriesCategoryName(categoryName);
        if (products.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        return products;
    }

    @Override
    public List<ProductsModel> searchColor(String color) {
        return productRepo.findByProductAttributesColor(color);
    }

    @Override
    public List<ProductsModel> searchType(String type) {
        return productRepo.findByProductAttributesType(type);
    }

    @Override
    public List<ProductsModel> RangerProduct(double minPrice, double maxPrice) {
        return productRepo.findByProductPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<ProductsModel> searchByFeature(String feature) {
        return productRepo.findByProductAttributesFeature(feature);

    }

    @Override
    public List<ProductsModel> RangerDiscount(double minDiscount, double maxDiscount) {
        return productRepo.findByProductDiscountBetween(minDiscount, maxDiscount);
    }


}
