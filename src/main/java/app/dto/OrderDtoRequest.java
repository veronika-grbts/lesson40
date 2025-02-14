package app.dto;

import app.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderDtoRequest(Long id, LocalDate creationDate, double totalCost,
                              List<Product> products) {
}
