package com.cmarinescu.backend.product.service;

import com.cmarinescu.backend.common.exception.EntityNotFoundException;
import com.cmarinescu.backend.olfactoryGroup.model.OlfactoryGroup;
import com.cmarinescu.backend.olfactoryGroup.repository.OlfactoryGroupRepository;
import com.cmarinescu.backend.product.dto.UpdateProductRequest;
import com.cmarinescu.backend.product.model.Product;
import com.cmarinescu.backend.product.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService implements IProductService{
    private ProductRepository productRepository;
    private OlfactoryGroupRepository olfactoryGroupRepository;

    public ProductService(ProductRepository productRepository, OlfactoryGroupRepository olfactoryGroupRepository) {
        this.productRepository = productRepository;
        this.olfactoryGroupRepository = olfactoryGroupRepository;
    }
    @Override
    public Product addProduct(Product product, List<Long> olfactoryGroupIds) {
        List<OlfactoryGroup> olfactoryGroups = olfactoryGroupRepository.findAllById(olfactoryGroupIds);

        if (olfactoryGroups.size() != olfactoryGroupIds.size()) {
            throw new EntityNotFoundException("One or more olfactory group IDs are invalid");
        }

        product.setOlfactoryGroups(olfactoryGroups);

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product updateProduct(Long id, UpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found" ));

        BeanUtils.copyProperties(request, product, getNullPropertyNames(request));

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found" ));

        product.setDeleted(true);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : src.getPropertyDescriptors()) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
