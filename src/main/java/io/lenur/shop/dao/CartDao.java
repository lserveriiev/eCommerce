package io.lenur.shop.dao;

import io.lenur.shop.domain.Cart;
import io.lenur.shop.exception.ModelNotFoundException;
import io.lenur.shop.persistence.Storage;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CartDao implements Dao<Cart> {
    @Override
    public Optional<Cart> get(Long id) {
        return Storage.carts
                .stream()
                .filter(x -> Objects.equals(id, x.getId()))
                .findFirst();
    }

    @Override
    public List<Cart> getAll() {
        return Storage.carts;
    }

    @Override
    public Cart create(Cart cart) {
        Storage.addCart(cart);

        return cart;
    }

    @Override
    public Cart update(Cart cart) {
        int index = getIndex(cart);
        Storage.carts.set(index, cart);

        return cart;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.carts.removeIf(x -> x.getId().equals(id));
    }

    private int getIndex(Cart cart) {
        for (int i=0; i < Storage.carts.size(); i++) {
            if (Objects.equals(cart.getId(), Storage.carts.get(i).getId())) {
                return i;
            }
        }

        throw new ModelNotFoundException();
    }
}
