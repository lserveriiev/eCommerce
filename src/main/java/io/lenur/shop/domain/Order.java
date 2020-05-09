package io.lenur.shop.domain;

import java.util.List;

public class Order {
    private Long id;
    private List<Product> products;
    private User user;

    public Order() {
    }

    public Order(List<Product> products, User user) {
        this.products = products;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", products=" + products +
                ", user=" + user +
                '}';
    }
}
