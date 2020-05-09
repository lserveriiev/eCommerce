package io.lenur.shop.dao;

import io.lenur.shop.domain.Order;
import io.lenur.shop.persistence.Storage;

import java.util.List;

public class OrderDao extends AbstractDao<Order> {
    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public Order create(Order order) {
        Storage.addOrder(order);

        return order;
    }

    @Override
    public Order update(Order order) {
        int index = getIndex(order);
        Storage.orders.set(index, order);

        return order;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.orders.removeIf(x -> x.getId().equals(id));
    }

}
