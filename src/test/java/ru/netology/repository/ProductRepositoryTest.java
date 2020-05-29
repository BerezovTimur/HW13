package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Tshirt;
import ru.netology.exception.NotFoundException;
import ru.netology.manager.ProductManager;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    private Book first = new Book(1,"Война и мир",1000,"Толстой");
    private Book second = new Book(2,"Отцы и дети",1000,"Тургенев");
    private Tshirt third = new Tshirt(3, "Run", 1500, "Nike");

    @BeforeEach
    void setup() {
        manager = new ProductManager(repository);
        manager.addProduct(first);
        manager.addProduct(second);
        manager.addProduct(third);
    }

    @Test
    void shouldRemoveIfExists() {
        int idToRemove = 1;
        manager.removeById(idToRemove);
        Product[] actual = manager.findAll();
        Product[] expected = new Product[]{second, third};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotRemoveIfNotExists() {
        int idToRemove = 4;
        assertThrows(NotFoundException.class, () -> manager.removeById(idToRemove));
    }
}