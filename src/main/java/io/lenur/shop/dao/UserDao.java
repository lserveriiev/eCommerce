package io.lenur.shop.dao;

import io.lenur.shop.domain.User;
import io.lenur.shop.exception.ModelNotFoundException;
import io.lenur.shop.persistence.Storage;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDao implements Dao<User> {
    @Override
    public Optional<User> get(Long id) {
        return Storage.users
                .stream()
                .filter(x -> Objects.equals(id, x.getId()))
                .findFirst();
    }

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

    private int getIndex(User user) {
        for (int i=0; i < Storage.users.size(); i++) {
            if (Objects.equals(user.getId(), Storage.users.get(i).getId())) {
                return i;
            }
        }

        throw new ModelNotFoundException();
    }
}
