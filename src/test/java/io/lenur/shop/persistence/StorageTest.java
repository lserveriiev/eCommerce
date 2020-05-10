package io.lenur.shop.persistence;

import io.lenur.shop.domain.Cart;
import io.lenur.shop.domain.Order;
import io.lenur.shop.domain.Product;
import io.lenur.shop.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StorageTest {
    @Test
    public void addCart() {
        Cart cart = new Cart(new ArrayList<>(), new User());
        Storage.addCart(cart);
        List<Cart> carts = Storage.getCarts();

        Assert.assertEquals(1, carts.size());
        Assert.assertEquals(Long.valueOf(1L), carts.get(0).getId());
    }

    @Test
    public void addOrder() {
        Order order = new Order(new ArrayList<>(), new User());
        Storage.addOrder(order);
        List<Order> orders = Storage.getOrders();

        Order orderPersisted = orders.get(0);
        Assert.assertEquals(1, orders.size());
        Assert.assertEquals(order, orderPersisted);
    }

    @Test
    public void addProduct() {
        Product product = new Product("product 1", 100.0);
        Storage.addProduct(product);
        List<Product> products = Storage.getProducts();

        Product productPersisted = products.get(0);
        Assert.assertEquals(1, products.size());
        Assert.assertEquals(product, productPersisted);
    }

    @Test
    public void addUser() {
        User user = new User("name", "user@example.com", "password");
        Storage.addUser(user);
        List<User> users = Storage.getUsers();

        User userPersisted = users.get(0);
        Assert.assertEquals(1, users.size());
        Assert.assertEquals(user, userPersisted);
    }
}
