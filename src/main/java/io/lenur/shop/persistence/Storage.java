package io.lenur.shop.persistence;

import io.lenur.shop.domain.Cart;
import io.lenur.shop.domain.Order;
import io.lenur.shop.domain.Product;
import io.lenur.shop.domain.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final static List<Cart> carts = new ArrayList<>();
    private final static List<User> users = new ArrayList<>();
    private final static List<Order> orders = new ArrayList<>();
    private final static List<Product> products = new ArrayList<>();

    private static Long latestCartId = 0L;
    private static Long latestUserId = 0L;
    private static Long latestOrderId = 0L;
    private static Long latestProductId = 0L;

    public static void addCart(Cart cart) {
        cart.setId(++latestCartId);
        carts.add(cart);
    }

    public static void addUser(User user) {
        user.setId(++latestUserId);
        users.add(user);
    }

    public static void addOrder(Order order) {
        order.setId(++latestOrderId);
        orders.add(order);
    }

    public static void addProduct(Product product) {
        product.setId(++latestProductId);
        products.add(product);
    }

    public static List<Cart> getCarts() {
        return carts;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static List<Product> getProducts() {
        return products;
    }
}
