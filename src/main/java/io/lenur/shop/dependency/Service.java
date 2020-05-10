package io.lenur.shop.dependency;

import io.lenur.di.annotation.Dependencies;
import io.lenur.di.annotation.Instance;

import io.lenur.shop.domain.Product;
import io.lenur.shop.domain.User;
import io.lenur.shop.service.CartService;
import io.lenur.shop.service.OrderService;
import io.lenur.shop.service.ProductService;
import io.lenur.shop.service.UserService;
import io.lenur.shop.service.impl.CartServiceImpl;
import io.lenur.shop.service.impl.OrderServiceImpl;
import io.lenur.shop.service.impl.ProductServiceImpl;
import io.lenur.shop.service.impl.UserServiceImpl;

@Dependencies
public class Service {
    @Instance
    public ProductService<Product> getProductService() {
        return new ProductServiceImpl();
    }

    @Instance
    public UserService<User> getUserService() {
        return new UserServiceImpl();
    }

    @Instance
    public CartService getCartService() {
        return new CartServiceImpl();
    }

    @Instance
    public OrderService getOrderService() {
        return new OrderServiceImpl();
    }
}
