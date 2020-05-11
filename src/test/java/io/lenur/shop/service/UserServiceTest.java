package io.lenur.shop.service;

import io.lenur.shop.Injector;
import io.lenur.shop.dao.impl.UserDao;
import io.lenur.shop.domain.User;
import io.lenur.shop.exception.ModelNotFoundException;
import io.lenur.shop.persistence.Storage;
import io.lenur.shop.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceTest {
    private final UserService<User> userService;

    public UserServiceTest() {
        final Map<Class<?>, Object> dependencies = new HashMap<>();
        dependencies.put(UserDao.class, new UserDao());
        Injector injector = new Injector(dependencies);

        userService = (UserService<User>) injector.getInstance(UserServiceImpl.class);
    }

    @Test
    public void getAll() {
        List<User> users = userService.getAll();
        List<User> usersFromStorage = Storage.getUsers();

        Assert.assertEquals(users, usersFromStorage);
    }

    @Test
    public void get() {
        User user = userService.get(1000_000L);
        Assert.assertNull(user);

        User userToCreate = new User();
        userService.create(userToCreate);

        User userPersisted = userService.get(userToCreate.getId());
        Assert.assertNotNull(userPersisted);
        Assert.assertEquals(userPersisted, userToCreate);
    }

    @Test
    public void update() {
        User user = new User("name", "email@example.com", "password");
        userService.create(user);

        user.setEmail("newEmail@example.com");

        userService.update(user);
        User userPersisted = userService.get(user.getId());
        Assert.assertEquals("newEmail@example.com", userPersisted.getEmail());
    }

    @Test(expected = ModelNotFoundException.class)
    public void updateEmptyUser() {
        userService.update(new User());
    }

    @Test
    public void delete() {
        User user = new User("name", "email@example.com", "password");
        userService.create(user);

        boolean deleted = userService.delete(user.getId());
        Assert.assertTrue(deleted);

        long count = Storage
                .getUsers()
                .stream()
                .filter(x -> x.equals(user))
                .count();

        Assert.assertEquals(0, count);
    }

    @After
    public void clearStorage() {
        Storage.getUsers().clear();
    }
}
