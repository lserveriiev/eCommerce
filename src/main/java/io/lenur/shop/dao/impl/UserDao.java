package io.lenur.shop.dao.impl;

import io.lenur.shop.domain.User;
import io.lenur.shop.persistence.Storage;

import java.util.List;

public class UserDao extends AbstractDao<User>{
    @Override
    public List<User> getAll() {
        return Storage.getUsers();
    }

    @Override
    public User create(User user) {
        Storage.addUser(user);

        return user;
    }

    @Override
    public User update(User user) {
        int index = getIndex(user);
        List<User> users = getAll();

        users.set(index, user);

        return user;
    }

    @Override
    public boolean delete(Long id) {
        List<User> users = getAll();

        return users.removeIf(x -> x.getId().equals(id));
    }
}
