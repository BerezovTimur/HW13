package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Tshirt;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.ProductRepository;


import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
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
    void shouldFindIfExists() {
        int idToFind = 3;
        manager.findById(idToFind);
        Product expected = third;
        Product actual = manager.findById(idToFind);
        assertEquals(expected, actual);
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
    void shouldReturnNullIfNotExists() {
        int idToFind = 4;
        repository.findById(idToFind);
        Product expected = null;
        Product actual = manager.findById(idToFind);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotRemoveIfNotExists() {
        int idToRemove = 4;
        assertThrows(NotFoundException.class, () -> manager.removeById(idToRemove));
    }
}