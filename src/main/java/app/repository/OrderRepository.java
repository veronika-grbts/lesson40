package app.repository;

import app.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    public Optional<Order> findById(Long id) {
        return orders.stream().filter(o -> o.getId().equals(id)).findFirst();
    }

    public boolean create(Order order) {
        return orders.add(order);
    }

    public Optional<List<Order>> fetchAll() {
        return Optional.of(new ArrayList<>(orders));
    }

    public boolean update(Long id, Order updatedOrder) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(id)) {
                orders.set(i, updatedOrder);
                return true;
            }
        }
        return false;
    }

    public boolean delete(Long id) {
        return orders.removeIf(order -> order.getId().equals(id));
    }

}
