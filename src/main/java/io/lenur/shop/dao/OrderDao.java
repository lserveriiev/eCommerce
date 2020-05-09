package io.lenur.shop.dao;

import io.lenur.shop.domain.Order;
import io.lenur.shop.exception.ModelNotFoundException;
import io.lenur.shop.persistence.Storage;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OrderDao implements Dao<Order> {
    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders
                .stream()
                .filter(x -> Objects.equals(id, x.getId()))
                .findFirst();
    }

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

    private int getIndex(Order order) {
        for (int i=0; i < Storage.orders.size(); i++) {
            if (Objects.equals(order.getId(), Storage.orders.get(i).getId())) {
                return i;
            }
        }

        throw new ModelNotFoundException();
    }
}
