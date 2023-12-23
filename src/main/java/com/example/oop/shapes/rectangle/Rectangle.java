package com.example.oop.shapes.rectangle;

import com.example.oop.shapes.Shape;

public class Rectangle extends Shape {
    private double base;
    private double height;

    public Rectangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double getArea() {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Сторона Треугольника должна быть положительной.");
        }
        return base * height;
    }

    @Override
    public double getPerimeter() {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Сторона Треугольника должна быть положительной.");
        }
        return 2 * base + 2 * height;
    }
}
