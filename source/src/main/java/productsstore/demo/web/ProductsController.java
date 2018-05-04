package productsstore.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import productsstore.demo.entities.Product;
import productsstore.demo.entities.User;
import productsstore.demo.services.base.ProductsService;
import productsstore.demo.services.base.UsersService;
import productsstore.demo.utils.loggers.base.LoggerProvider;

import java.io.InvalidObjectException;
import java.security.Principal;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;
    private final UsersService usersService;

    private final LoggerProvider<ProductsController> logger;

    @Autowired
    public ProductsController(ProductsService productsService,
                              UsersService usersService,
                              LoggerProvider<ProductsController> logger) {
        this.productsService = productsService;
        this.usersService = usersService;
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
        Product product = new Product();
        model.addAttribute("product", product);
        return "products/create";
    }

    @PostMapping("/create")
    public String create(
        @ModelAttribute Product product,
        Principal principal
    ) {
        try {
            User user = usersService.getUserByUsername(principal.getName());
            product.setUser(user);
            productsService.createProduct(product);
            return "redirect:/";
        } catch (InvalidObjectException e) {
            return "redirect:/products/create";
        }
    }
}
