package io.lenur.shop.domain;

import java.util.List;

public class Cart implements Identifiable {
    private Long id;
    private List<Product> products;
    private User user;

    public Cart() {
    }

    public Cart(List<Product> products, User user) {
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
        return "Cart{" +
                "id=" + id +
                ", products=" + products +
                ", user=" + user +
                '}';
    }
}
