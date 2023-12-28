package com.example.oop.shapes.ellipse;

import com.example.oop.shapes.Shape;

public class Ellipse extends Shape {
    private double axisX;
    private double axisY;

    public Ellipse(double axisX, double axisY) {
        if (axisX <= 0 || axisY <= 0) {
            throw new IllegalArgumentException("Сторона должна быть положительной.");
        } else if (axisX < axisY) {
            throw new IllegalArgumentException("Большая полуось эллипса не может быть меньше малой полуоси.");
        }
        this.axisX = axisX;
        this.axisY = axisY;
    }

    @Override
    public double getArea() {
        return Math.PI * axisX * axisY;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * axisX;
    }
}
