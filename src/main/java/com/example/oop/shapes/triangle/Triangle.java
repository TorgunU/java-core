package com.example.oop.shapes.triangle;

import com.example.oop.shapes.Shape;

public class Triangle extends Shape {
    private double base;
    private double sideA;
    private double sideB;

    public Triangle(double base, double sideA, double sideB) {
        this.base = base;
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public double getArea() {
        if (base <= 0 || sideA <= 0 || sideB <= 0) {
            throw new IllegalArgumentException("Сторона должна быть положительной.");
        }
        return 0.5 * base * sideA;
    }

    @Override
    public double getPerimeter() {
        if (base <= 0 || sideA <= 0 || sideB <= 0) {
            throw new IllegalArgumentException("Сторона должна быть положительной.");
        }
        return base + sideA + sideB;
    }
}
