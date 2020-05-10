package io.lenur.shop.service.impl;

import io.lenur.di.annotation.Inject;
import io.lenur.shop.dao.impl.ProductDao;
import io.lenur.shop.domain.Product;
import io.lenur.shop.exception.ModelNotFoundException;
import io.lenur.shop.service.ProductService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductServiceImpl implements ProductService<Product> {
    @Inject
    private ProductDao productDao;

    @Override
    public Product get(Long id) {
        Optional<Product> product = productDao.get(id);

        return product.orElse(null);
    }

    @Override
    public List<Product> getAll() {
        return Collections.unmodifiableList(productDao.getAll());
    }

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product update(Product product) {
        Objects.requireNonNull(product);

        Product productPersist = get(product.getId());
        if (productPersist == null) {
            throw new ModelNotFoundException();
        }

        productPersist.setName(product.getName());
        productPersist.setPrice(product.getPrice());

        return productDao.update(productPersist);
    }

    @Override
    public boolean delete(Long id) {
        return productDao.delete(id);
    }
}