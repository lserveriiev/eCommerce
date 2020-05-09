package io.lenur.shop.dao;

import io.lenur.shop.domain.Cart;
import io.lenur.shop.persistence.Storage;

import java.util.List;

public class CartDao extends AbstractDao<Cart> {
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
}
