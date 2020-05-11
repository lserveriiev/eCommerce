package io.lenur.shop.dao;

import io.lenur.shop.dao.impl.CartDaoImpl;
import io.lenur.shop.domain.Cart;
import io.lenur.shop.domain.User;
import io.lenur.shop.persistence.Storage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class CartDaoTest {
    private final CartDao cartDao = new CartDaoImpl();

    @Test
    public void getAll() {
        List<Cart> carts = cartDao.getAll();
        List<Cart> cartsFromStorage = Storage.getCarts();

        Assert.assertEquals(carts, cartsFromStorage);
    }

    @Test
    public void create() {
        Cart cart = new Cart();

        cartDao.create(cart);
        Assert.assertNotNull(cart.getId());
    }

    @Test
    public void get() {
        Optional<Cart> cartNull = cartDao.get(1000_000_000L);
        Assert.assertTrue(cartNull.isEmpty());

        Cart cart = new Cart();
        Cart cartStored = cartDao.create(cart);

        Optional<Cart> cartPersisted = cartDao.get(cartStored.getId());
        Assert.assertFalse(cartPersisted.isEmpty());
    }

    @Test
    public void update() {
        Cart cart = new Cart();
        Cart cartStored = cartDao.create(cart);
        cartStored.setUser(new User());
        cartDao.update(cartStored);

        Optional<Cart> cartPersisted = cartDao.get(cartStored.getId());
        Assert.assertFalse(cartPersisted.isEmpty());
        Assert.assertEquals(cartPersisted.get().getUser(), cartStored.getUser());
    }

    @Test
    public void delete() {
        Cart cart = new Cart();
        cartDao.create(cart);

        boolean isDeleted = cartDao.delete(cart.getId());
        Assert.assertTrue(isDeleted);

        Optional<Cart> cartPersisted = cartDao.get(cart.getId());
        Assert.assertTrue(cartPersisted.isEmpty());
    }

    @After
    public void clearStorage() {
        Storage.getCarts().clear();
    }
}
