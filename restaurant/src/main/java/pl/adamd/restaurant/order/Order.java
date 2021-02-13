package pl.adamd.restaurant.order;

import pl.adamd.restaurant.menu.Menu;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer table;
    @ManyToMany
    private List<Menu> menuItemList;
    private BigDecimal countPrice;

    public Order(Long id, Integer table, List<Menu> menuItemList, BigDecimal countPrice) {
        this.id = id;
        this.table = table;
        this.menuItemList = menuItemList;
        this.countPrice = countPrice;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public int getTable() {
        return table;
    }

    public List<Menu> getMenuItemList() {
        return menuItemList;
    }

    public BigDecimal getCountPrice() {
        return countPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTable(Integer table) {
        this.table = table;
    }

    public void setMenuItemList(List<Menu> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public void setCountPrice(BigDecimal countPrice) {
        this.countPrice = countPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", table=" + table +
                ", menuItemList=" + menuItemList +
                ", countPrice=" + countPrice +
                '}';
    }
}
