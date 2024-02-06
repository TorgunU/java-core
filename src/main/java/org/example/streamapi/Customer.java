package org.example.streamapi;

import java.util.Set;

public record Customer(Long id, String name, Long level, Set<Order> orders) {
}
