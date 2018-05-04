package productsstore.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import productsstore.demo.entities.Product;
import productsstore.demo.services.base.ProductsService;
import productsstore.demo.services.base.UsersService;

import java.util.List;

@Controller
public class HomeController {

    private final ProductsService productsService;
    private final UsersService usersService;

    @Autowired
    public HomeController(ProductsService productsService, UsersService usersService) {
        this.productsService = productsService;
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = productsService.getAllProducts();
        model.addAttribute("message", "Hello!");
        model.addAttribute("products", products);
        return "index";
    }
}
