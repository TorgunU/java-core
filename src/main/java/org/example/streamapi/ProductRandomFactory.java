package org.example.streamapi;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ProductRandomFactory {
    public static Set<Product> generateProducts() {
        Random random = new Random();
        Set<Product> productSet = new HashSet<>();
        ProductCategory[] categories = ProductCategory.values();
        for (int i = 1; i <= 20; i++) {
            ProductCategory category = categories[random.nextInt(0, categories.length)];
            productSet.add(new Product(
                    (long) i,
                    "Product №" + " " + random.nextInt(1, 1000),
                    category.getLabel(),
                    BigDecimal.valueOf(random.nextInt(50, 200))));
        }
        return productSet;
    }
}
