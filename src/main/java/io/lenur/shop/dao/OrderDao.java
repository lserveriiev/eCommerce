package io.lenur.shop.dao;

import io.lenur.shop.domain.Order;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> getUserOrders(Long userId);
}