package com.cmarinescu.backend.order.controller;

import com.cmarinescu.backend.order.dto.CreateOrderRequest;
import com.cmarinescu.backend.order.dto.OrderResponse;
import com.cmarinescu.backend.order.mapper.OrderMapper;
import com.cmarinescu.backend.order.model.Order;
import com.cmarinescu.backend.order.service.OrderService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private OrderService orderService;
    private OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("public/order")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request){
        Order order = orderMapper.requestToModel(request);
        OrderResponse response = orderMapper.modelToResponse(orderService.createOrder(order, request.getProductList(), request.getAddressRequest()));
        return ResponseEntity.ok().body(response);
    }
}
