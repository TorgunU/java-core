package com.example.oop.shapes.ellipse;

import com.example.oop.shapes.Shape;

public class Ellipse extends Shape {
    private double largeSemiAxis;
    private double smallSemiAxis;

    public Ellipse(double largeSemiAxis, double smallSemiAxis) {
        this.largeSemiAxis = largeSemiAxis;
        this.smallSemiAxis = smallSemiAxis;
    }

    @Override
    public double getArea() {
        if (largeSemiAxis <= 0 || smallSemiAxis <= 0) {
            throw new IllegalArgumentException("Сторона должна быть положительной.");
        }
        return Math.PI * largeSemiAxis * smallSemiAxis;
    }

    @Override
    public double getPerimeter() {
        if (largeSemiAxis <= 0 || smallSemiAxis <= 0) {
            throw new IllegalArgumentException("Сторона должна быть положительной.");
        }
        return 2 * Math.PI * largeSemiAxis;
    }
}
