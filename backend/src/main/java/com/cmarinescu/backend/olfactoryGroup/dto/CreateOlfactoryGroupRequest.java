package com.cmarinescu.backend.olfactoryGroup.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateOlfactoryGroupRequest {
    @NotBlank(message = "Name is required")
    private String name;

    private String description;
}
