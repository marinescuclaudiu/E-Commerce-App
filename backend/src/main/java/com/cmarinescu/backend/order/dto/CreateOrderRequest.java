package com.cmarinescu.backend.order.dto;

import com.cmarinescu.backend.common.dto.AddressRequest;
import com.cmarinescu.backend.order.model.PaymentMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String Notes;

    @NotNull(message = "Address is required")
    private AddressRequest addressRequest;

    @NotNull(message = "Should have at least one product in list")
    private List<OrderProductRequest> productList;
}
