package io.lenur.shop.dao.impl;

import io.lenur.shop.dao.Dao;
import io.lenur.shop.domain.Identifiable;
import io.lenur.shop.exception.ModelNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {
    public Optional<T> get(Long id) {
        final List<T> items = getAll();

        return items
                .stream()
                .filter(x -> Objects.equals(id, x.getId()))
                .findFirst();
    }

    protected int getIndex(T item) {
        final List<T> items = getAll();

        for (int i = 0; i < items.size(); i++) {
            if (Objects.equals(item.getId(), items.get(i).getId())) {
                return i;
            }
        }

        throw new ModelNotFoundException();
    }
}
