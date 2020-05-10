package io.lenur.shop.domain;

import java.util.List;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(id, products, user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Cart cart = (Cart) obj;

        return Objects.equals(cart.id, id)
                && Objects.equals(cart.products, products)
                && Objects.equals(cart.user, user);
    }
}
