package io.lenur.shop.dao.impl;

import io.lenur.shop.domain.Product;
import io.lenur.shop.persistence.Storage;

import java.util.List;

public class ProductDao extends AbstractDao<Product> {
    @Override
    public List<Product> getAll() {
        return Storage.getProducts();
    }

    @Override
    public Product create(Product product) {
        Storage.addProduct(product);

        return product;
    }

    @Override
    public Product update(Product product) {
        int index = getIndex(product);
        List<Product> products = getAll();

        products.set(index, product);

        return product;
    }

    @Override
    public boolean delete(Long id) {
        List<Product> products = getAll();

        return products.removeIf(x -> x.getId().equals(id));
    }
}
