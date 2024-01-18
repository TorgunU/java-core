package org.example.streamapi;

import java.time.LocalDate;
import java.util.Set;

public record Order(Long id, LocalDate orderDate, LocalDate deliveryDate, String status, Set<Product> products) {
}
