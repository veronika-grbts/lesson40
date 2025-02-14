package app.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Order {
    private Long id;
    private LocalDate creationDate;
    private double totalCost;
    private List<Product> products;

    public Order() {
    }

    public Order(LocalDate creationDate, double totalCost,  List<Product> products) {
        this.id = generateRandomId();
        this.creationDate = creationDate;
        this.totalCost = totalCost;
        this.products = products;
    }
    private Long generateRandomId() {
        Random random = new Random();
        return random.nextLong(1, 1000000);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public  List<Product> getProducts() {
        return products;
    }

    public void setProducts( List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(totalCost, order.totalCost) == 0 && Objects.equals(id, order.id) && Objects.equals(creationDate, order.creationDate) && Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, totalCost, products);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", totalCost=" + totalCost +
                ", products=" + products +
                '}';
    }
}
