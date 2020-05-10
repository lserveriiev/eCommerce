package io.lenur.shop.dao;

import io.lenur.shop.domain.Cart;

import java.util.Optional;

public interface CartDao extends Dao<Cart> {
    Optional<Cart> getByUserId(Long userId);
}