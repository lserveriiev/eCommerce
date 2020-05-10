package io.lenur.shop.service;

import io.lenur.shop.domain.Cart;
import io.lenur.shop.domain.Product;

public interface CartService {
    Cart create(Cart cart);

    Cart addProduct(Cart cart, Product product);

    boolean deleteProduct(Cart cart, Product product);

    void clear(Cart cart);

    Cart getByUserId(Long userId);

    boolean delete(Cart cart);
}