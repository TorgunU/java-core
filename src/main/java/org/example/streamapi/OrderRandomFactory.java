package org.example.streamapi;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderRandomFactory {
    public static Set<Order> generateOrders(Set<Product> products) {
        Random random = new Random();
        Set<Order> orderSet = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            LocalDate orderDate = LocalDate.of(
                    2021,
                    random.nextInt(2, 5),
                    random.nextInt(1, 29));
            LocalDate deliveryDate = orderDate.plusDays(random.nextInt(1, 15));
            orderSet.add(new Order(
                    (long) i,
                    orderDate,
                    deliveryDate,
                    "Status №" + " " + random.nextInt(1, 1000),
                    getShuffledProducts(random, products)));
        }
        return orderSet;
    }

    public static Order generateExtraOrder(int day, Set<Product> products) {
        Random random = new Random();
        return new Order(
                (long) random.nextInt(1000),
                LocalDate.of(
                        2021,
                        3,
                        day),
                LocalDate.of(
                        2021,
                        3,
                        day + 1),
                "Status №" + " " + random.nextInt(1, 1000),
                products
                );
    }

    private static Set<Product> getShuffledProducts(Random random, Set<Product> products) {
        List<Product> shuffledProducts = new ArrayList<>(products);
        Collections.shuffle(shuffledProducts, random);
        int randomInt = random.nextInt(1, products.size() + 1);
        return shuffledProducts.stream()
                .limit(randomInt)
                .collect(Collectors.toSet());
    }
}
