package com.clinpride.SecurityPostgres.Products.ProductServices;

import com.clinpride.SecurityPostgres.Products.ProductControllers.NotFoundException;
import com.clinpride.SecurityPostgres.Products.ProductRepo.ProductRepo;
import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    public Page<ProductsModel> getAllProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepo.findAll(pageable);
    }

    @Override
    public Optional<ProductsModel> getOneProduct(String id) {
        return productRepo.findById(id);
    }

    @Override
    public Page<ProductsModel> searchCategory(String categoryName,  int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ProductsModel> products = productRepo.findByProductCategoriesCategoryName(categoryName);
        if (products.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        return productRepo.findByProductCategoriesCategoryName(categoryName, pageable);
    }

    @Override
    public Page<ProductsModel> searchColor(String color, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ProductsModel> productsColor = productRepo.findByProductAttributesColor(color);

        if (productsColor.isEmpty()) {
            throw new NotFoundException("Color not found");
        }
        return productRepo.findByProductAttributesColor(color, pageable);
    }

    @Override
    public Page<ProductsModel> searchType(String type,  int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ProductsModel> productsColor = productRepo.findByProductAttributesType(type);
        if (productsColor.isEmpty()) {
            throw new NotFoundException("Type not found");
        }
        return productRepo.findByProductAttributesType(type, pageable);
    }

    @Override
    public Page<ProductsModel> RangerProduct(double minPrice, double maxPrice,  int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ProductsModel> productsPriceBetween = productRepo.findByProductPriceBetween(minPrice, maxPrice);
        if (productsPriceBetween.isEmpty()) {
            throw new NotFoundException("range not found");
        }
        return productRepo.findByProductPriceBetween(minPrice, maxPrice, pageable);
    }

    @Override
    public Page<ProductsModel> searchByFeature(String feature,  int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        List<ProductsModel> productsPriceBetween = productRepo.findByProductAttributesFeature(feature);
        if (productsPriceBetween.isEmpty()) {
            throw new NotFoundException("feature not found");
        }
        return productRepo.findByProductAttributesFeature(feature , pageable);
    }

    @Override
    public Page<ProductsModel> RangerDiscount(double minDiscount, double maxDiscount,  int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ProductsModel> productsPriceBetween = productRepo.findByProductDiscountBetween(minDiscount, maxDiscount);
        if (productsPriceBetween.isEmpty()) {
            throw new NotFoundException("discount range not found");
        }
        return productRepo.findByProductDiscountBetween(minDiscount, maxDiscount, pageable );
    }

    @Override
    public Page<ProductsModel> getAllProductByName(String ProductName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ProductsModel> productsProductName= productRepo.findByProductName(ProductName);
        if (productsProductName.isEmpty()) {
            throw new NotFoundException("product not found");
        }
        return productRepo.findByProductName( ProductName, pageable );
    }

}
