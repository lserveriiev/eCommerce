package io.lenur.shop.service;

import io.lenur.shop.Injector;
import io.lenur.shop.dao.impl.ProductDao;
import io.lenur.shop.domain.Product;
import io.lenur.shop.exception.ModelNotFoundException;
import io.lenur.shop.persistence.Storage;
import io.lenur.shop.service.impl.ProductServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceTest {
    private final ProductService<Product> productService;

    public ProductServiceTest() {
        final Map<Class<?>, Object> dependencies = new HashMap<>();
        dependencies.put(ProductDao.class, new ProductDao());
        Injector injector = new Injector(dependencies);

        productService = (ProductService<Product>) injector
                .getInstance(ProductServiceImpl.class);
    }

    @Test
    public void getAll() {
        List<Product> products = productService.getAll();
        List<Product> productsFromStorage = Storage.getProducts();

        Assert.assertEquals(products, productsFromStorage);
    }

    @Test
    public void get() {
        Product product = productService.get(1000_000L);
        Assert.assertNull(product);

        Product productToCreate = new Product();
        productService.create(productToCreate);

        Product productPersisted = productService.get(productToCreate.getId());
        Assert.assertNotNull(productPersisted);
        Assert.assertEquals(productPersisted, productToCreate);
    }

    @Test
    public void update() {
        Product product = new Product("name", 200.2);
        productService.create(product);

        product.setName("new name");

        productService.update(product);
        Product productPersisted = productService.get(product.getId());
        Assert.assertEquals("new name", productPersisted.getName());
    }

    @Test(expected = ModelNotFoundException.class)
    public void updateEmptyProduct() {
        productService.update(new Product());
    }

    @Test
    public void delete() {
        Product product = new Product("name", 200.2);
        productService.create(product);

        boolean deleted = productService.delete(product.getId());
        Assert.assertTrue(deleted);

        long count = Storage
                .getProducts()
                .stream()
                .filter(x -> x.equals(product))
                .count();

        Assert.assertEquals(0, count);
    }

    @After
    public void clearStorage() {
        Storage.getProducts().clear();
    }
}
