package productsstore.demo.services;

import org.springframework.stereotype.Service;
import productsstore.demo.entities.Product;
import productsstore.demo.repositories.base.GenericRepository;
import productsstore.demo.services.base.ProductsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HibernateProductsService implements ProductsService {

    private static final int PAGE_SIZE = 10;
    private final GenericRepository<Product> productsRepository;

    public HibernateProductsService(GenericRepository<Product> productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productsRepository.getAll();
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        List<Product> products = productsRepository.getAll();

        return products.stream()
            .filter(product -> product.getCategories()
                .stream()
                .anyMatch(category ->
                    category.getName().equals(categoryName))
            )
            .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(int id) {
        return productsRepository.getById(id);
    }

    @Override
    public List<Product> getAllProductsByPage(int pageNumber) {
        int fromIndex = pageNumber * PAGE_SIZE;
        int toIndex = (pageNumber + 1) * PAGE_SIZE;
        return getAllProducts()
            .subList(fromIndex, toIndex);
    }

    @Override
    public List<Product> getProductsByCategoryAndPage(String category, int pageNumber) {
        int fromIndex = pageNumber * PAGE_SIZE;
        int toIndex = (pageNumber + 1) * PAGE_SIZE;

        return getProductsByCategory(category)
            .subList(fromIndex, toIndex);
    }
}
