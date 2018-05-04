package productsstore.demo.services.base;

import org.springframework.security.core.userdetails.UserDetailsService;
import productsstore.demo.entities.User;

public interface UsersService extends UserDetailsService {

    User getUserByUsername(String username);

    void create(User user);
}
