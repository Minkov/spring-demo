package productsstore.demo;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import productsstore.demo.entities.Product;
import productsstore.demo.repositories.HibernateRepository;
import productsstore.demo.repositories.base.GenericRepository;

@Configuration
public class DiConfig {

    @Bean
    @Autowired
    GenericRepository<Product> provideGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Product> repo = new HibernateRepository<>(sessionFactory);
        repo.setEntityClass(Product.class);

        return repo;
    }

    @Bean
    SessionFactory provideSessionFactory() {
        return HibernateUtils.getSessionFactory();
    }
}
