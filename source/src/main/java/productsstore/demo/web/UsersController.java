package productsstore.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import productsstore.demo.entities.User;
import productsstore.demo.services.base.UsersService;

import java.security.Principal;

@Controller
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/auth/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/auth/register")
    public String register(@ModelAttribute User user) {
        usersService.create(user);
        return "auth/register";
    }

    @GetMapping("/users/{username}")
    public String profile(@PathVariable String username, Model model, Principal principal) {
        User profile = usersService.getUserByUsername(username);
        String title = profile.getUsername();

        if (principal != null) {
            User loggedInUser = usersService.getUserByUsername(principal.getName());
            if (loggedInUser.getId() == profile.getId()) {
                title = "My profile";
            }
        }

        model.addAttribute("title", title);
        model.addAttribute("profile", profile);

        return "users/profile";
    }
}
