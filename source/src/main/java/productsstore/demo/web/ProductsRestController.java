package productsstore.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import productsstore.demo.entities.Product;
import productsstore.demo.services.base.ProductsService;

import java.util.List;

@RestController
public class ProductsRestController {

    private final ProductsService productsService;

    @Autowired
    public ProductsRestController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @RequestMapping(value = "/products")
    public List<Product> getProductsByCategory(
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String page) {
        if (category == null) {
            if (page == null) {
                return productsService.getAllProducts();
            } else {
                return productsService.getAllProductsByPage(Integer.parseInt(page));
            }
        } else {
            if (page == null) {
                return productsService.getProductsByCategory(category);
            } else {
                return productsService.getProductsByCategoryAndPage(category, Integer.parseInt(page));
            }
        }
    }

    @RequestMapping("/products/{id}")
    public Product getProductDetails(@PathVariable String id) {
        return productsService.getProductById(Integer.parseInt(id));
    }
}
