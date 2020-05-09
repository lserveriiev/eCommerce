package io.lenur.shop;

import io.lenur.di.Dependency;
import io.lenur.di.PackageContext;
import io.lenur.shop.domain.Order;
import io.lenur.shop.domain.Product;
import io.lenur.shop.domain.User;
import io.lenur.shop.service.OrderService;
import io.lenur.shop.service.ProductService;
import io.lenur.shop.service.UserService;

import java.util.Arrays;

public class Application {
    private static PackageContext packageContext = Dependency.init("io.lenur.shop");

    public static void main(String[] args) {
        UserService<User> userService = (UserService<User>) packageContext.getInstance(UserService.class);
        User user1 = new User("user", "user1@example.com", "password");
//        System.out.println(userService.get(1L));//null
//        System.out.println(userService.getAll());//[]
//
          User user1Persisted = userService.create(user1);
//        System.out.println(userService.get(user1Persisted.getId()));//User{id=1, name='user', email='user1@example.com', password='password'}
//        user1Persisted.setPassword("new password");
//        System.out.println(userService.update(user1Persisted));//User{id=1, name='user', email='user1@example.com', password='new password'}
//        userService.delete(user1Persisted.getId());
//        System.out.println(userService.get(1L));//null

//        ////---- PRODUCT ----////
        ProductService<Product> productService = (ProductService<Product>) packageContext.getInstance(ProductService.class);
        Product product1 = new Product("product1", 22);
//        System.out.println(productService.get(1L));//null
//        System.out.println(productService.getAll());//[]
//
        Product product1Persisted = productService.create(product1);
//        System.out.println(productService.get(product1Persisted.getId()));//Product{id=1, name='product1', price=22.0}
//        product1Persisted.setName("new name");
//        System.out.println(productService.update(product1Persisted));//Product{id=1, name='new name', price=22.0}
//        productService.delete(product1Persisted.getId());
//        System.out.println(productService.get(1L));//null

        ////---- ORDER ----////
        OrderService<Order> orderService = (OrderService<Order>) packageContext.getInstance(OrderService.class);
        Order order1 = new Order(Arrays.asList(product1Persisted), user1Persisted);
        System.out.println(productService.get(1L));//null
        System.out.println(productService.getAll());//[]

        Order order1Persisted = orderService.create(order1);
        //Order{
        // id=1,
        // products=[Product{id=1, name='product1', price=22.0}],
        // user=User{id=1, name='user', email='user1@example.com', password='password'}
        // }
        System.out.println(orderService.get(order1Persisted.getId()));
    }
}
