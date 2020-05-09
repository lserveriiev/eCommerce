package io.lenur.shop;

import io.lenur.di.Dependency;
import io.lenur.di.PackageContext;
import io.lenur.shop.domain.User;
import io.lenur.shop.service.UserService;

public class Application {
    private static PackageContext packageContext = Dependency.init("io.lenur.shop");

    public static void main(String[] args) {
        UserService<User> userService = (UserService<User>) packageContext.getInstance(UserService.class);
        User user1 = new User("user", "user1@example.com", "password");
        System.out.println(userService.get(1L));//null
        System.out.println(userService.getAll());//[]

        User user1Persisted = userService.create(user1);
        System.out.println(userService.get(user1Persisted.getId()));//User{id=1, name='user', email='user1@example.com', password='password'}
        user1Persisted.setPassword("new password");
        System.out.println(userService.update(user1Persisted));//User{id=1, name='user', email='user1@example.com', password='new password'}
        userService.delete(user1Persisted.getId());
        System.out.println(userService.get(1L));//null
    }
}
