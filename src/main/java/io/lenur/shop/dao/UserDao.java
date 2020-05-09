package io.lenur.shop.dao;

import io.lenur.shop.domain.User;
import io.lenur.shop.persistence.Storage;

import java.util.List;

public class UserDao extends AbstractDao<User>{
    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public User create(User user) {
        Storage.addUser(user);

        return user;
    }

    @Override
    public User update(User user) {
        int index = getIndex(user);
        Storage.users.set(index, user);

        return user;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.users.removeIf(x -> x.getId().equals(id));
    }
}
