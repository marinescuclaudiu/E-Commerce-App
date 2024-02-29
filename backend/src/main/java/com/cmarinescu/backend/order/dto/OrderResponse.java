package com.cmarinescu.backend.order.dto;

import com.cmarinescu.backend.common.dto.AddressRequest;
import com.cmarinescu.backend.order.model.OrderStatus;
import com.cmarinescu.backend.order.model.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class OrderResponse {
    private Long id;

    private OrderStatus status;

    private PaymentMethod paymentMethod;

    private String Notes;

    private String email;

    private AddressRequest addressRequest;

    private Set<OrderProductResponse> orderProducts;

    private Float totalPrice;
}
