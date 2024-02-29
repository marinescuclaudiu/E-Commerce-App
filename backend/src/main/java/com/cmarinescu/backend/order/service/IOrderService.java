package com.cmarinescu.backend.order.service;

import com.cmarinescu.backend.common.dto.AddressRequest;
import com.cmarinescu.backend.order.dto.OrderProductRequest;
import com.cmarinescu.backend.order.model.Order;

import java.util.List;

public interface IOrderService{
    Order createOrder(Order order, List<OrderProductRequest> productList, AddressRequest addressRequest);
    Order getOrder(Long orderId);

}
