package pl.adamd.restaurant.menu;

import pl.adamd.restaurant.products.Product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    private List<Product> productList;
    private BigDecimal price;

    public Menu(Long id, String name, List<Product> productList, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.productList = productList;
        this.price = price;
    }

    public Menu() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List<Product> productList) {
        this.productList = productList;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productList=" + productList +
                ", price=" + price +
                '}';
    }
}
