package com.cmarinescu.backend.product.service;

import com.cmarinescu.backend.product.dto.UpdateProductRequest;
import com.cmarinescu.backend.product.model.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product, List<Long> olfactoryGroupIds);
    List<Product> getAllProducts();
    Product updateProduct(Long id, UpdateProductRequest request);
    void deleteProduct(Long id);
}
