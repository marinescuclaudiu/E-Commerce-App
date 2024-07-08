package com.cmarinescu.backend.product.controller;

import com.cmarinescu.backend.product.dto.CreateProductRequest;
import com.cmarinescu.backend.product.dto.ProductResponse;
import com.cmarinescu.backend.product.dto.UpdateProductRequest;
import com.cmarinescu.backend.product.mapper.ProductMapper;
import com.cmarinescu.backend.product.model.Product;
import com.cmarinescu.backend.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;
    private ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("admin/product")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody CreateProductRequest request){
        Product product = productMapper.requestToModel(request);
        ProductResponse response = productMapper.modelToResponse(productService.addProduct(product, request.getOlfactoryGroupsIds()));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("public/product")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        List<ProductResponse> responseList = productMapper.modelListToResponseList(productList);
        return ResponseEntity.ok().body(responseList);
    }

    @PatchMapping("admin/product/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest request){
        ProductResponse response = productMapper.modelToResponse(productService.updateProduct(id, request));
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("admin/product/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
