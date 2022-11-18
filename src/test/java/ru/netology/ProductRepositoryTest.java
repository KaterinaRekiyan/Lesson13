package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {
    Product product1 = new Book(2, "Наёмник", 450, "В.Корн");
    Product product2 = new Smartphone(20, "Iphone", 70_000, "Apple");
    Product product3 = new Product(100, "Платье", 7000);

//    тест на добавление
    @Test
    public void saveTest() {
        ProductRepository repo = new ProductRepository();
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);

        Product[] expected = {product1, product2, product3};
        Product[] actual = repo.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeByIdTest() {
        ProductRepository repo = new ProductRepository();
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.removeById(product2.getId());

        Product[] expected = {product1, product3};
        Product[] actual = repo.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }
}
