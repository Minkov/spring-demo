package productsstore.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import productsstore.demo.entities.Product;
import productsstore.demo.services.base.ProductsService;
import productsstore.demo.utils.base.LoggerProvider;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;
    private final LoggerProvider<ProductsController> logger;

    @Autowired
    public ProductsController(ProductsService productsService,
                              LoggerProvider<ProductsController> logger) {
        this.productsService = productsService;
        this.logger = logger;
        this.logger.setClass(ProductsController.class);
    }

    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        logger.info("In product details");

        Product product = productsService.getProductById(Integer.parseInt(id));
        model.addAttribute("product", product);

        return "products/details";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "products/create";
    }

    @PostMapping("/create")
    public String create(@RequestBody Product product) {

        return "index";
    }

}
