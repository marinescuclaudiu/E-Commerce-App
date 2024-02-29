package com.cmarinescu.backend.order.mapper;

import com.cmarinescu.backend.common.dto.AddressRequest;
import com.cmarinescu.backend.order.model.OrderProduct;
import com.cmarinescu.backend.order.dto.CreateOrderRequest;
import com.cmarinescu.backend.order.dto.OrderProductResponse;
import com.cmarinescu.backend.order.dto.OrderResponse;
import com.cmarinescu.backend.order.model.Order;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class OrderMapper {
    public Order requestToModel(CreateOrderRequest request) {

        return new Order(
                request.getPaymentMethod(),
                request.getNotes());
    }

    public OrderResponse modelToResponse(Order order){
        AddressRequest addressRequest = new AddressRequest(
                order.getAddress().getStreet(),
                order.getAddress().getCity(),
                order.getAddress().getZipCode(),
                order.getAddress().getCountry());

        Set<OrderProductResponse> orderProducts = new HashSet<>();
        float totalPrice = 0F;

        for(OrderProduct product : order.getOrderProducts()){

            orderProducts.add(new OrderProductResponse(
                                product.getId(),
                                product.getQuantity(),
                                product.getTotalPrice()));

            totalPrice += product.getTotalPrice();
        }

        String userEmail = order.getUser() != null ? order.getUser().getEmail() : null;

        return new OrderResponse(
                order.getId(),
                order.getStatus(),
                order.getPaymentMethod(),
                order.getNotes(),
                userEmail,
                addressRequest,
                orderProducts,
                totalPrice);
    }
}
