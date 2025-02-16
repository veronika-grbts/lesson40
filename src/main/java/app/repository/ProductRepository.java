package app.repository;

import app.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    @PostConstruct
    private void init() {
        products.add(new Product(1L, "Помидоры", 25.5));
        products.add(new Product(2L, "Картошка", 15.0));
        products.add(new Product(3L, "Огурцы", 30.0));
        products.add(new Product(4L, "Морковь", 20.0));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Product> findAllById(List<Long> ids) {
        return products.stream()
                .filter(p -> ids.contains(p.getId()))
                .collect(Collectors.toList());
    }
}
