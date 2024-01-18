package org.example;

import org.example.streamapi.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Создаем продукты
        System.out.println("Список продуктов:");
        Set<Product> products = ProductRandomFactory.generateProducts();
        products.forEach(System.out::println);

        // Создаем заказы
        System.out.println("Список заказов:");
        Set<Order> orders = OrderRandomFactory.generateOrders(products);

        // Создаем дополнительные заказы с датами для задания 7 и 9
        orders.add(OrderRandomFactory.generateExtraOrder(
                15,
                products));
        orders.add(OrderRandomFactory.generateExtraOrder(
                14,
                products));
        orders.forEach(System.out::println);

        // Создаем покупателей
        System.out.println("Список покупателей:");
        Set<Customer> customers = CustomerRandomFactory.generateCustomers(orders);
        customers.forEach(System.out::println);

        // Задание 1
        System.out.println("\nЗадание 1:");
        List<Product> resultTask1 = doTask1(customers);
        resultTask1.forEach(System.out::println);

        // Задание 2
        System.out.println("\nЗадание 2:");
        List<Order> resultTask2 = doTask2(customers);
        resultTask2.forEach(System.out::println);

        // Задание 3
        System.out.println("\nЗадание 3:");
        Map<List<Product>, Float> result3 = doTask3(customers);
        result3.keySet().forEach(
                key -> System.out.println("Sum of discount = " + result3.get(key)));
        System.out.println("Toys: " + result3.entrySet());

        // Задание 4
        System.out.println("\nЗадание 4:");
        List<Order> resultTask4 = doTask4(customers);
        resultTask4.forEach(System.out::println);

        // Задание 5
        System.out.println("\nЗадание 5:");
        List<Product> resultTask5 = doTask5(customers);
        resultTask5.forEach(System.out::println);

        // Задание 6
        System.out.println("\nЗадание 6:");
        List<Order> resultTask6 = doTask6(customers);
        resultTask6.forEach(System.out::println);

        // Задание 7
        System.out.println("\nЗадание 7:");
        List<Order> resultTask7 = doTask7(customers);
        resultTask7.forEach(System.out::println);

        // Задание 8
        System.out.println("\nЗадание 8:");
        float result8 = doTask8(customers);
        System.out.println(result8);

        // Задание 9
        System.out.println("\nЗадание 9:");
        double result9 = doTask9(customers);
        System.out.println(result9);

        // Задание 10
        System.out.println("\nЗадание 10:");
        Map<String, Double> booksOrderStatistic = doTask10(customers);
        booksOrderStatistic.keySet()
                .forEach(key -> System.out.println(key + ":" + booksOrderStatistic.get(key)));

        // Задание 11
        System.out.println("\nЗадание 11:");
        Map<Long, Integer> productIdCountMap = doTask11(customers);
        productIdCountMap.keySet()
                .forEach(id ->
                        System.out.println(
                                "Id:" + id.toString()
                                        + " Количество:" + productIdCountMap.get(id).toString() + " "));

        // Задание 12
        System.out.println("\nЗадание 12:");
        Map<Customer, List<Order>> customerOrdersMap = doTask12(customers);
        customerOrdersMap.keySet()
                .forEach(System.out::println);

        // Задание 13
        System.out.println("\nЗадание 13:");
        Map<Order, Double> orderSumMap = doTask13(customers);
        orderSumMap.keySet()
                .forEach(order -> System.out.printf(
                        "Id %s: сумма %s;\n", order.id(), orderSumMap.get(order).toString()));

        // Задание 14
        System.out.println("\nЗадание 14:");
        Map<String, List<String>> categoryNamedProducts = doTask14(customers);
        categoryNamedProducts.keySet()
                .forEach(category -> System.out.printf(
                        "Категория %s: %s;\n", category, categoryNamedProducts.get(category).toString()));

        // Задание 15
        System.out.println("\nЗадание 15:");
        Map<String, Product> categoryExpensiveProducts = doTask15(customers);
        categoryExpensiveProducts.keySet()
                .forEach(category -> System.out.printf(
                        "Категория %s: %s;\n", category, categoryExpensiveProducts.get(category).toString()));
    }

    private static List<Product> doTask1(Set<Customer> customers) {
        return customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .flatMap(order -> order.products().stream())
                .filter(product -> product.category().equals(ProductCategory.BOOKS.getLabel()))
                .filter(product -> product.price().intValue() > 100)
                .distinct()
                .toList();
    }

    private static List<Order> doTask2(Set<Customer> customers) {
        return customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .filter(order -> order.products().stream()
                        .anyMatch(product -> product.category().equals(
                                ProductCategory.ChildrenProducts.getLabel())))
                .distinct()
                .toList();
    }

    private static Map<List<Product>, Float> doTask3(Set<Customer> customers) {
        List<Product> toys = customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .flatMap(order -> order.products().stream())
                .filter(product -> product.category().equals(ProductCategory.Toys.getLabel()))
                .distinct()
                .toList();
        Float sum = toys.stream()
                .map(product -> product.price().floatValue() * 0.1f)
                .reduce(Float::sum)
                .orElseThrow(() ->
                        new IllegalStateException("Продуктов с категорией Toys нет в списке!"));
        Map<List<Product>, Float> toysDiscountStatistic = new HashMap<>();
        toysDiscountStatistic.put(toys, sum);
        return toysDiscountStatistic;
    }

    private static List<Order> doTask4(Set<Customer> customers) {
        return customers.stream()
                .filter(customer -> customer.level() == 2)
                .flatMap(customer -> customer.orders().stream())
                .filter(order ->
                        order.orderDate().isAfter(LocalDate.of(2021, 2, 1))
                            && order.orderDate().isBefore(LocalDate.of(2021, 4, 1)))
                .toList();
    }

    private static List<Product> doTask5(Set<Customer> customers) {
        return customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .flatMap(order -> order.products().stream())
                .distinct()
                .sorted(Comparator.comparing(Product::price))
                .limit(2)
                .toList();
    }

    private static List<Order> doTask6(Set<Customer> customers) {
        return customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .distinct()
                .sorted(Comparator.comparing(Order::orderDate).reversed())
                .limit(3)
                .toList();
    }

    private static List<Order> doTask7(Set<Customer> customers) {
        return customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .filter(order -> order.orderDate().isEqual(
                        LocalDate.of(2021, Month.MARCH, 15)))
                .distinct()
                .peek(order -> System.out.println("Id заказа:" + order.id().toString()))
                .toList();
    }

    private static float doTask8(Set<Customer> customers) {
        return customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .filter(order -> order.orderDate().getMonth().equals(Month.FEBRUARY))
                .flatMap(order -> order.products().stream())
                .map(product -> product.price().floatValue())
                .reduce(Float::sum)
                .orElseThrow(() -> new IllegalStateException("Сумма заказов некорректна!"));
    }

    private static double doTask9(Set<Customer> customers) {
        return customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .filter(order -> order.orderDate().isEqual(
                        LocalDate.of(2021, Month.MARCH, 14)))
                .flatMap(order -> order.products().stream())
                .mapToDouble(product -> product.price().doubleValue())
                .average()
                .orElseThrow(() -> new IllegalStateException("Сумма заказов некорректна!"));
    }

    private static Map<String, Double> doTask10(Set<Customer> customers) {
        Map<String, Double> booksOrderStatistic = new HashMap<>();
        List<Double> prices = customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .flatMap(order -> order.products().stream())
                .filter(product -> product.category().equals(ProductCategory.BOOKS.getLabel()))
                .map(product -> product.price().doubleValue())
                .toList();
        double sum = prices.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        booksOrderStatistic.put("Сумма", sum);
        double average = prices.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElseThrow(() ->
                        new IllegalStateException("Невозможно вычислить среднюю цена заказа!"));
        booksOrderStatistic.put("Среднее", average);
        double max = prices.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Невозможно вычислить максимальную сумму заказа некорректна!"));
        booksOrderStatistic.put("Максимум", max);
        double min = prices.stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .orElseThrow(() ->
                        new IllegalStateException("Невозможно вычслить минимальную цену товара!"));
        booksOrderStatistic.put("Минимум", min);
        double count = prices.stream().count();
        booksOrderStatistic.put("Количество", count);
        return booksOrderStatistic;
    }

    private static Map<Long, Integer> doTask11(Set<Customer> customers) {
        Map<Long, Integer> productCountStatistic = new HashMap<>();
        customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .forEach(order -> productCountStatistic.put(order.id(), order.products().size()));
        return productCountStatistic;
    }

    private static Map<Customer, List<Order>> doTask12(Set<Customer> customers) {
        Map<Customer, List<Order>> customerOrdersMap = new HashMap<>();
        customers.forEach(customer ->
                        customerOrdersMap.put(customer, customer.orders().stream().toList()));
        return customerOrdersMap;
    }

    private static Map<Order, Double> doTask13(Set<Customer> customers) {
        Map<Order, Double> orderSumMap = new HashMap<>();
        customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .forEach(order ->
                        orderSumMap.put(
                                order,
                                order.products().stream()
                                        .mapToDouble(product -> product.price().doubleValue())
                                        .sum()));
        return orderSumMap;
    }

    private static Map<String, List<String>> doTask14(Set<Customer> customers) {
        return customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .flatMap(order -> order.products().stream())
                .distinct()
                .collect(Collectors.groupingBy(Product::category,
                        Collectors.mapping(
                                Product::name, Collectors.toList()
                        )));
    }

    private static Map<String, Product> doTask15(Set<Customer> customers) {
        return customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .flatMap(order -> order.products().stream())
                .distinct()
                .collect(Collectors.groupingBy(Product::category,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Product::price)),
                                productOptional -> productOptional.orElse(null))));
    }
}