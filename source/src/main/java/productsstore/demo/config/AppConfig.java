package productsstore.demo.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import productsstore.demo.entities.Product;
import productsstore.demo.entities.User;
import productsstore.demo.repositories.HibernateRepository;
import productsstore.demo.repositories.base.GenericRepository;
import productsstore.demo.utils.validators.ProductValidator;
import productsstore.demo.utils.validators.base.Validator;

@Configuration
public class AppConfig {
    @Bean
    @Autowired
    GenericRepository<Product> provideProductsGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Product> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Product.class);

        return repo;
    }


    @Bean
    @Autowired
    GenericRepository<User> provideUsersGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<User> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(User.class);

        return repo;
    }

    @Bean
    SessionFactory provideSessionFactory() {
        return HibernateUtils.getSessionFactory();
    }

    @Bean
    Validator<Product> provideProductValidator() {
        return new ProductValidator();
    }
}
