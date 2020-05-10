package io.lenur.shop.dao.impl;

import io.lenur.shop.dao.CartDao;
import io.lenur.shop.domain.Cart;
import io.lenur.shop.persistence.Storage;

import java.util.List;
import java.util.Optional;

public class CartDaoImpl extends AbstractDao<Cart> implements CartDao {
    @Override
    public Optional<Cart> getByUserId(Long userId) {
        return getAll()
                .stream()
                .filter(x -> x.getUser().getId().equals(userId))
                .findFirst();
    }

    @Override
    public List<Cart> getAll() {
        return Storage.getCarts();
    }

    @Override
    public Cart create(Cart cart) {
        Storage.addCart(cart);

        return cart;
    }

    @Override
    public Cart update(Cart cart) {
        int index = getIndex(cart);

        List<Cart> carts = getAll();
        carts.set(index, cart);

        return cart;
    }

    @Override
    public boolean delete(Long id) {
        List<Cart> carts = getAll();

        return carts.removeIf(x -> x.getId().equals(id));
    }
}
