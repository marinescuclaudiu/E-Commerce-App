package com.cmarinescu.backend.product.dto;

import com.cmarinescu.backend.product.model.Concentration;
import com.cmarinescu.backend.product.model.GenderTarget;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductResponse {
    private Long id;

    private String name;

    private String description;

    private String brand;

    private Integer size;

    private GenderTarget genderTarget;

    private Concentration concentration;

    private boolean isNiche;

    private Integer quantity;

    private Float price;

    private List<String> olfactoryGroups;

    private boolean isAvailable;
}
