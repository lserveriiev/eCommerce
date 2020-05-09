package io.lenur.shop.service.impl;

import io.lenur.di.annotation.Inject;
import io.lenur.shop.dao.CartDao;
import io.lenur.shop.domain.Cart;
import io.lenur.shop.exception.ModelNotFoundException;
import io.lenur.shop.service.CartService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CartServiceImpl implements CartService<Cart> {
    @Inject
    private CartDao cartDao;

    @Override
    public Cart get(Long id) {
        Optional<Cart> cart = cartDao.get(id);

        return cart.orElse(null);
    }

    @Override
    public List<Cart> getAll() {
        return Collections.unmodifiableList(cartDao.getAll());
    }

    @Override
    public Cart create(Cart cart) {
        return cartDao.create(cart);
    }

    @Override
    public Cart update(Cart cart) {
        Objects.requireNonNull(cart);

        Cart cartPersist = get(cart.getId());
        if (cartPersist == null) {
            throw new ModelNotFoundException();
        }

        cartPersist.setUser(cart.getUser());
        cartPersist.setProducts(cart.getProducts());

        return cartDao.update(cartPersist);
    }

    @Override
    public boolean delete(Long id) {
        return cartDao.delete(id);
    }
}