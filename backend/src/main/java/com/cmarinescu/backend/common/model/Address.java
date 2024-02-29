package com.cmarinescu.backend.common.model;

import com.cmarinescu.backend.order.model.Order;
import com.cmarinescu.backend.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Street is required")
    @Column(nullable = false)
    private String street;

    @NotBlank(message = "City is required")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "Zip code is required")
    @Column(nullable = false)
    private String zipCode;

    @NotBlank(message = "Country is required")
    @Column(nullable = false)
    private String country;

    @OneToOne(mappedBy = "address")
    private User user;

    @OneToMany(mappedBy = "address")
    private Set<Order> orders;

    public Address(){

    }

    public Address(String street, String city, String zipCode, String country) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }
}
