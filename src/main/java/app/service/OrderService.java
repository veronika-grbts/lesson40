package app.service;

import app.dto.OrderDtoRequest;
import app.entity.Order;
import app.entity.Product;
import app.repository.OrderRepository;
import app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    @Autowired
    ProductRepository productRepository;

    public boolean create(OrderDtoRequest request) {

        Order order = new Order(request.creationDate(), request.totalCost(), request.products());
        return repository.create(order);
    }


    public List<Order> fetchAll() {
        return repository.fetchAll()
                .orElse(Collections.emptyList());
    }

    public Order fetchById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean update(Long id, OrderDtoRequest request) {
        Order updatedOrder = new Order(request.creationDate(), request.totalCost(), request.products());
        return repository.update(id, updatedOrder);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}
