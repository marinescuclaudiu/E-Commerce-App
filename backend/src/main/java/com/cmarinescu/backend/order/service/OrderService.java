package com.cmarinescu.backend.order.service;

import com.cmarinescu.backend.common.dto.AddressRequest;
import com.cmarinescu.backend.common.exception.UnauthorizedAccessException;
import com.cmarinescu.backend.common.model.Address;
import com.cmarinescu.backend.common.repository.AddressRepository;
import com.cmarinescu.backend.order.model.OrderProduct;
import com.cmarinescu.backend.common.exception.EntityNotFoundException;
import com.cmarinescu.backend.order.dto.OrderProductRequest;
import com.cmarinescu.backend.order.model.Order;
import com.cmarinescu.backend.order.repository.OrderRepository;
import com.cmarinescu.backend.product.model.Product;
import com.cmarinescu.backend.product.repository.ProductRepository;
import com.cmarinescu.backend.user.model.User;
import com.cmarinescu.backend.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService{
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private AddressRepository addressRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public Order createOrder(Order order, List<OrderProductRequest> productList, AddressRequest addressRequest) {
        Set<OrderProduct> orderProducts = new HashSet<>();

        List<Long> productIds = productList.stream()
                .map(OrderProductRequest::getProductId)
                .collect(Collectors.toList());
        Map<Long, Product> productMap = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        for (OrderProductRequest orderProduct : productList) {
            Product product = productMap.get(orderProduct.getProductId());
            if (product != null) {
                orderProducts.add(new OrderProduct(orderProduct.getQuantity(), product.getPrice() * orderProduct.getQuantity(), order, product));
            } else {
                throw new EntityNotFoundException("Product with id " + orderProduct.getProductId() + " doesn't exist");
            }
        }

        order.setOrderProducts(orderProducts);

        Address existingAddress = addressRepository.findByStreetAndCityAndZipCodeAndCountry(
                addressRequest.getStreet(),
                addressRequest.getCity(),
                addressRequest.getZipCode(),
                addressRequest.getCountry());

        if(existingAddress != null){
            order.setAddress(existingAddress);
        }else {
            Address address = new Address(
                    addressRequest.getStreet(),
                    addressRequest.getCity(),
                    addressRequest.getZipCode(),
                    addressRequest.getCountry());
            addressRepository.save(address);

            order.setAddress(address);
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (email.equals("anonymousUser")) {
            Optional<User> user = userRepository.findUserByEmail(email);
            user.ifPresent(order::setUser);
        }

        return orderRepository.save(order);
    }


    @Override
    public Order getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new EntityNotFoundException("Order with id " + orderId + " doesn't exist"));

        if (!isAuthorizedToAccessOrder(order)) {
            throw new UnauthorizedAccessException("Access denied");
        }

        return order;
    }

    private boolean isAuthorizedToAccessOrder(Order order) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        boolean isUser = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"));

        return isAdmin || (isUser && Objects.equals(authentication.getName(), order.getUser().getEmail()));
    }

}
