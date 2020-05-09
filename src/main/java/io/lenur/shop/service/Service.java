package io.lenur.shop.service;

import java.util.List;

public interface Service<T> {
     
    T get(Long id);
     
    List<T> getAll();
     
    T create(T t);

    T update(T t);

    boolean delete(Long id);
}