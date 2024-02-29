package com.cmarinescu.backend.product.mapper;

import com.cmarinescu.backend.olfactoryGroup.model.OlfactoryGroup;
import com.cmarinescu.backend.product.dto.CreateProductRequest;
import com.cmarinescu.backend.product.dto.ProductResponse;
import com.cmarinescu.backend.product.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    public Product requestToModel(CreateProductRequest request) {
        return new Product(
                request.getName(),
                request.getDescription(),
                request.getBrand(),
                request.getSize(),
                request.getGenderTarget(),
                request.getConcentration(),
                request.isNiche(),
                request.getQuantity(),
                request.getPrice());
    }

    public ProductResponse modelToResponse(Product product){
        List<String> olfactoryGroupNames = product.getOlfactoryGroups().stream()
                .map(OlfactoryGroup::getName)
                .toList();

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getBrand(),
                product.getSize(),
                product.getGenderTarget(),
                product.getConcentration(),
                product.isNiche(),
                product.getQuantity(),
                product.getPrice(),
                olfactoryGroupNames,
                product.isAvailable());
    }

    public List<ProductResponse> modelListToResponseList(List<Product> productList) {
        List<ProductResponse> responseList = new ArrayList<>();
        for(Product product : productList){
            responseList.add(modelToResponse(product));
        }
        return responseList;
    }
}
