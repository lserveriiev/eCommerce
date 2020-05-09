package io.lenur.shop.dao;

import io.lenur.shop.domain.Product;
import io.lenur.shop.exception.ModelNotFoundException;
import io.lenur.shop.persistence.Storage;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductDao implements Dao<Product> {
    @Override
    public Optional<Product> get(Long id) {
        return Storage.products
                .stream()
                .filter(x -> Objects.equals(id, x.getId()))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

    @Override
    public Product create(Product product) {
        Storage.addProduct(product);

        return product;
    }

    @Override
    public Product update(Product product) {
        int index = getIndex(product);
        Storage.products.set(index, product);

        return product;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.products.removeIf(x -> x.getId().equals(id));
    }

    private int getIndex(Product product) {
        for (int i=0; i < Storage.products.size(); i++) {
            if (Objects.equals(product.getId(), Storage.products.get(i).getId())) {
                return i;
            }
        }

        throw new ModelNotFoundException();
    }
}
