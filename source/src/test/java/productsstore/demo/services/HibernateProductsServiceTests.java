package productsstore.demo.services;

import org.junit.Assert;
import org.junit.Test;
import productsstore.demo.entities.Product;
import productsstore.demo.repositories.base.GenericRepository;

import java.util.Arrays;
import java.util.List;

public class HibernateProductsServiceTests {
    @Test
    public void getAllProduct_whenProductsAreAvaible_expectTheProducts() {
        // Arrange
        List<Product> products = Arrays.asList(
            new Product(),
            new Product()
        );

        GenericRepository<Product> repo = new GenericRepository<Product>() {
            @Override
            public List<Product> getAll() {
                return products;
            }

            @Override
            public Product getById(int id) {
                return null;
            }

            @Override
            public Product create(Product entity) {
                return null;
            }
        };

        ProductsServiceImpl service = new ProductsServiceImpl(repo);

        // Act

        List<Product> actualProducts = service.getAllProducts();

        // Assert

        Assert.assertArrayEquals(
            actualProducts.toArray(),
            products.toArray()
        );
    }

    @Test
    public void getById_whenNoProductWithId_expectNull() {
        // Arrange
        List<Product> products = Arrays.asList(
            new Product(),
            new Product()
        );

        GenericRepository<Product> repo = new GenericRepository<Product>() {
            @Override
            public List<Product> getAll() {
                return products;
            }

            @Override
            public Product getById(int id) {
                return null;
            }

            @Override
            public Product create(Product entity) {
                return null;
            }
        };

        ProductsServiceImpl service = new ProductsServiceImpl(repo);

        // Act

        Product product = service.getProductById(-1);

        // Assert

        Assert.assertNull(product);
    }

    @Test(expected = Exception.class)
    public void create_whenProductNameWithLen2_expectToThrow() {
        GenericRepository<Product> repo = new GenericRepository<Product>() {
            @Override
            public List<Product> getAll() {
                return null;
            }

            @Override
            public Product getById(int id) {
                return null;
            }

            @Override
            public Product create(Product entity) {
                return null;
            }
        };

        ProductsServiceImpl service = new ProductsServiceImpl(repo);

        Product product = new Product();
        product.setName("aa");

        // Act
        service.createProduct(product);
    }

    @Test
    public void create_whenProductIsValid_expectToHasId() {
        int id = 1;
        GenericRepository<Product> repo = new GenericRepository<Product>() {
            @Override
            public List<Product> getAll() {
                return null;
            }

            @Override
            public Product getById(int id) {
                return null;
            }

            @Override
            public Product create(Product entity) {
                entity.setId(id);
                return entity;
            }
        };

        ProductsServiceImpl service = new ProductsServiceImpl(repo);

        Product product = new Product();
        product.setName("aaaa");

        // Act
        service.createProduct(product);

        // Assert

        Assert.assertEquals(id, product.getId());
    }
}
