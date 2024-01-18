package org.example.streamapi;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerRandomFactory {
    public static Set<Customer> generateCustomers(Set<Order> orders) {
        Random random = new Random();
        Set<Customer> customerSet = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            customerSet.add(new Customer(
                    (long) i,
                    "Customer №" + " " + random.nextInt(1, 1000),
                    random.nextLong(1, 5),
                    getShuffledOrders(random, orders)));
        }
        return customerSet;
    }

    private static Set<Order> getShuffledOrders(Random random, Set<Order> orders) {
        List<Order> shuffledOrders = new ArrayList<>(orders);
        Collections.shuffle(shuffledOrders, random);
        int randomInt = random.nextInt(1, orders.size() + 1);
        return shuffledOrders.stream()
                .limit(randomInt)
                .collect(Collectors.toSet());
    }
}
