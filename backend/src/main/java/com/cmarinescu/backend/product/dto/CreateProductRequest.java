package com.cmarinescu.backend.product.dto;

import com.cmarinescu.backend.product.model.Concentration;
import com.cmarinescu.backend.product.model.GenderTarget;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateProductRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotNull(message = "Size is required")
    @Min(value = 0, message = "Size must be positive")
    private Integer size;

    @NotNull(message = "Gender target is required")
    @Enumerated(EnumType.STRING)
    private GenderTarget genderTarget;

    @NotNull(message = "Concentration is required")
    @Enumerated(EnumType.STRING)
    private Concentration concentration;

    @NotNull
    private boolean isNiche;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be positive")
    private Integer quantity;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be positive")
    private Float price;

    @NotNull(message = "Olfactory group id is required")
    private List<Long> olfactoryGroupsIds;
}
