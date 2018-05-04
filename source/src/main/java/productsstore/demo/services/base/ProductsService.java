package productsstore.demo.services.base;

import productsstore.demo.entities.Product;

import java.io.InvalidObjectException;
import java.util.List;

public interface ProductsService {
    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    Product getProductById(int id);

    List<Product> getAllProductsByPage(int pageNumber);

    List<Product> getProductsByCategoryAndPage(String category, int pageNumber);

    void createProduct(Product product) throws InvalidObjectException;
}
