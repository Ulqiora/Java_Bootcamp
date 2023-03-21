package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductsRepositoryJdbcImplTest {
    private ProductsRepository repository;
    private DataSource dataSource;

    final List<Product> FIND_ALL = Arrays.asList(
            new Product(1L, "a", 1L),
            new Product(2L, "b", 2L),
            new Product(3L, "c", 3L),
            new Product(4L, "d", 4L),
            new Product(5L, "e", 5L),
            new Product(6L, "f", 6L));
    final Product FIND_BY_ID = new Product(1L, "a", 1L);
    final Product UPDATED_PRODUCT = new Product(5L, "edited", 5L);
    final Product SAVE_PRODUCT = new Product(7L, "saved", 7L);
    final List<Product> PRODUCTS_AFTER_DELETE = Arrays.asList(
            new Product(1L, "a", 1L),
            new Product(2L, "b", 2L),
            new Product(4L, "d", 4L),
            new Product(5L, "e", 5L),
            new Product(6L, "f", 6L));

    @BeforeEach
    public void init() {
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        repository = new ProductsRepositoryJdbcImpl(dataSource);
    }
    @AfterEach
    public void delete() {
        dataSource=null;
        repository=null;
    }

    @Test
    public void findAllTest() {
        assertEquals(FIND_ALL, repository.findAll());
    }

    @Test
    public void findByIdTest() throws SQLException {
        assertEquals(FIND_BY_ID, repository.findById(1L).get());
        assertEquals(Optional.empty(), repository.findById(10L));
        assertEquals(Optional.empty(), repository.findById(null));
    }
    @Test
    public void updateTest() throws SQLException {
        repository.update(UPDATED_PRODUCT);
        assertEquals(UPDATED_PRODUCT, repository.findById(5L).get());
    }

    @Test
    public void saveTest() throws SQLException {
        repository.save(SAVE_PRODUCT);
        assertEquals(SAVE_PRODUCT, repository.findById(7L).get());
    }

    @Test
    public void deleteTest() throws SQLException {
        repository.delete(3L);
        assertFalse(repository.findById(3L).isPresent());
    }
}