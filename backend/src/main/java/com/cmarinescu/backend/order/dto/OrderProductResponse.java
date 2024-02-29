package com.cmarinescu.backend.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderProductResponse {
    private Long id;

    Integer quantity;

    Float totalPrice;
}
