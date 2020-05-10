package io.lenur.shop.dao.impl;

import io.lenur.shop.dao.OrderDao;
import io.lenur.shop.domain.Order;
import io.lenur.shop.persistence.Storage;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    @Override
    public List<Order> getUserOrders(Long userId) {
        List<Order> orders = getAll();

        return orders
                .stream()
                .filter(x -> x.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getAll() {
        return Storage.getOrders();
    }

    @Override
    public Order create(Order order) {
        Storage.addOrder(order);

        return order;
    }

    @Override
    public Order update(Order order) {
        int index = getIndex(order);

        List<Order> orders = getAll();

        orders.set(index, order);

        return order;
    }

    @Override
    public boolean delete(Long id) {
        List<Order> orders = getAll();

        return orders.removeIf(x -> x.getId().equals(id));
    }
}
