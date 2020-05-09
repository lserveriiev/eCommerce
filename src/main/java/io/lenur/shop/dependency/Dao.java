package io.lenur.shop.dependency;

import io.lenur.di.annotation.Dependencies;
import io.lenur.di.annotation.Instance;
import io.lenur.shop.dao.CartDao;
import io.lenur.shop.dao.OrderDao;
import io.lenur.shop.dao.ProductDao;
import io.lenur.shop.dao.UserDao;

@Dependencies
public class Dao {
    @Instance
    public ProductDao getProductDao() {
        return new ProductDao();
    }

    @Instance
    public UserDao getUserDao() {
        return new UserDao();
    }

    @Instance
    public CartDao getCartDao() {
        return new CartDao();
    }

    @Instance
    public OrderDao getOrderDao() {
        return new OrderDao();
    }
}
