package com.cmarinescu.backend.product.model;

import com.cmarinescu.backend.order.model.OrderProduct;
import com.cmarinescu.backend.olfactoryGroup.model.OlfactoryGroup;
import com.cmarinescu.backend.review.model.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Description is required")
    @Column(nullable = false)
    private String description;

    @NotBlank(message = "Brand is required")
    @Column(nullable = false)
    private String brand;

    @NotNull(message = "Size is required")
    @Min(value = 0, message = "Size must be positive")
    @Column(nullable = false)
    private Integer size;

    @NotNull(message = "Gender target is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderTarget genderTarget;

    @NotNull(message = "Concentration is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Concentration concentration;

    @NotNull
    @Column(nullable = false)
    private boolean isNiche;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be positive")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be positive")
    @Column(nullable = false)
    private Float price;

    @NotNull
    @Column(nullable = false)
    private boolean isAvailable;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(
            name = "product_olfactory_group",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "olfactory_group_id")
    )
    private List<OlfactoryGroup> olfactoryGroups;

    @OneToMany(mappedBy = "product")
    private Set<OrderProduct> orderProducts;

    public Product(){

    }

    public Product(String name, String description, String brand, Integer size, GenderTarget genderTarget, Concentration concentration, boolean isNiche, Integer quantity, Float price) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.size = size;
        this.genderTarget = genderTarget;
        this.concentration = concentration;
        this.isNiche = isNiche;
        this.quantity = quantity;
        this.price = price;
        this.isAvailable = true;
    }
}
