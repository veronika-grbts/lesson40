package app.service;

import app.entity.Product;
import app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findProductsByIds(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

}
