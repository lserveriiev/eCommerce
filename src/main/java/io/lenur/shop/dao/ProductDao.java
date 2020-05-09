package io.lenur.shop.dao;

import io.lenur.shop.domain.Product;
import io.lenur.shop.persistence.Storage;

import java.util.List;

public class ProductDao extends AbstractDao<Product> {
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
}
