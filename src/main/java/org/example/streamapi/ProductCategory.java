package org.example.streamapi;

public enum ProductCategory {
    BOOKS("Books"),
    CHILDREN_PRODUCTS("Children's products"),
    TOYS("Toys");

    private final String label;

    ProductCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
