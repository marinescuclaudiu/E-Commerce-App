package com.cmarinescu.backend.olfactoryGroup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "olfactory_group")
public class OlfactoryGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    private String description;

    public OlfactoryGroup(){

    }

    public OlfactoryGroup(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
