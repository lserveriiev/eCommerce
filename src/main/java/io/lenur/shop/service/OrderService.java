package io.lenur.shop.service;

import io.lenur.shop.domain.Cart;
import io.lenur.shop.domain.Order;
import io.lenur.shop.domain.User;

import java.util.List;

public interface OrderService extends Service<Order> {
    Order completeOrder(Cart cart, User user);

    List<Order> getUserOrders(Long userId);
}