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

    //    тест на добавление
    @BeforeEach
    public void setupAdd() {
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
    }

    @Test
    public void addTest() {
        Product[] expected = {product1, product2, product3};
        Product[] actual = manager.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }


    // тест на подошедшие продукты

    @Test
    public void searchByTest() {
        manager.searchBy("Анастасия");
        Product[] expected = {product1};
        Product[] actual = manager.getProducts();
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
}
