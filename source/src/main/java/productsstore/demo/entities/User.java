package productsstore.demo.entities;

import productsstore.demo.entities.base.ModelEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements ModelEntity {
    private int id;
    private String username;
    private String password;
    private Set<Product> products;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Set<Product> getProducts() {
        return products;
    }

    void setProducts(Set<Product> products) {
        this.products = products;
    }
}
