package io.lenur.shop.service.impl;

import io.lenur.di.annotation.Inject;
import io.lenur.shop.dao.impl.UserDao;
import io.lenur.shop.domain.User;
import io.lenur.shop.exception.ModelNotFoundException;
import io.lenur.shop.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserServiceImpl implements UserService<User> {
    @Inject
    private UserDao userDao;

    @Override
    public User get(Long id) {
        Optional<User> user = userDao.get(id);

        return user.orElse(null);
    }

    @Override
    public List<User> getAll() {
        return Collections.unmodifiableList(userDao.getAll());
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User update(User user) {
        Objects.requireNonNull(user);

        User userPersist = get(user.getId());
        if (userPersist == null) {
            throw new ModelNotFoundException();
        }

        userPersist.setName(user.getName());
        userPersist.setEmail(user.getEmail());
        userPersist.setPassword(user.getPassword());

        return userDao.update(userPersist);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }
}