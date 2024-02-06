package org.example.streamapi;

import java.math.BigDecimal;

public record Product(Long id, String name, String category, BigDecimal price) {
}
