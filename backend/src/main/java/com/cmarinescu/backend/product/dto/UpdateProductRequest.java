package com.cmarinescu.backend.product.dto;

import com.cmarinescu.backend.product.model.Concentration;
import com.cmarinescu.backend.product.model.GenderTarget;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateProductRequest {
    private String name;

    private String description;

    private String brand;

    private Integer size;

    private GenderTarget genderTarget;

    private Concentration concentration;

    private boolean isNiche;

    private Integer quantity;

    private Float price;

    private Boolean isAvailable;
}
