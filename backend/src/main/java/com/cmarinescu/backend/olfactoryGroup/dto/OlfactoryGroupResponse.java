package com.cmarinescu.backend.olfactoryGroup.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class OlfactoryGroupResponse {
    private Long id;
    private String name;
    private String description;
}