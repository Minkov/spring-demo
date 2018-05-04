package productsstore.demo.entities;

import productsstore.demo.entities.base.ModelEntity;

import javax.persistence.*;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "products")
public class Product implements ModelEntity {
    int id;
    String name;
    float price;
    int quantity;
    private Set<Category> categories;
    private User user;

    public Product() {

    }

    public Product(String name, float price, int quantity) {
        this(-1, name, price, quantity);
    }

    public Product(int id, String name, float price, int quantity) {
        setId(id);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setCategories(new HashSet<>());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", length = 25, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price", nullable = false)
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return MessageFormat.format(
            "({0}, {1}, {2}, {3}, {4})",
            getId(),
            getName(),
            getPrice(),
            getQuantity(),
            getCategories()
                .stream()
                .map(Category::toString)
                .collect(Collectors.joining(", "))
        );
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "products_categories",
        joinColumns = {@JoinColumn(name = "product_id")},
        inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}