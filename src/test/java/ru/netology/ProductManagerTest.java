package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductManagerTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Product product1 = new Book(4, "Анастасия", 500, "В. Мегре");
    Product product2 = new Smartphone(23, "Core", 30000, "Samsung");
    Product product3 = new Product(104, "Брюки", 2000);
    Product product4 = new Product(105, "Брюки", 1500);

    //    тест на добавление
    @BeforeEach
    public void setupAdd() {
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);
    }

    @Test
    public void addTest() {
        Product[] expected = {product1, product2, product3, product4};
        Product[] actual = manager.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }


    // тест на подошедшие продукты

    @Test
    public void searchByTest() {
        Product[] expected = {product1};
        Product[] actual = manager.searchBy("Анастасия");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByNoTest() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("Мастер");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByTooTest() {
        Product[] expected = {product3, product4};
        Product[] actual = manager.searchBy("Брюки");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void matchesTest() {
        boolean expected = true;
        boolean actual = manager.matches(product1, "Анастасия");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void matchesNotTest() {
        boolean expected = false;
        boolean actual = manager.matches(product2, "Анастасия");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testRemove() {
        repo.removeById(4);
        Product[] expected = {product2, product3, product4};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void exceptionTest() {
        Assertions.assertThrows(NotFoundException.class,
                () ->repo.removeById(1));
    }
}
