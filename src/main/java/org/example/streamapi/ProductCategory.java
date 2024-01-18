package org.example.streamapi;

public enum ProductCategory {
    BOOKS("Books"),
    ChildrenProducts("Children's products"),
    Toys("Toys");

    private final String label;

    ProductCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
