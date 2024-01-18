package com.example.oop.shapes.triangle;

import com.example.oop.shapes.Shape;

public class Triangle extends Shape {
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        if (sideC <= 0 || sideA <= 0 || sideB <= 0) {
            throw new IllegalArgumentException("Сторона должна быть положительной.");
        } else if (sideA + sideB < sideC
                || sideA + sideC < sideB
                || sideB + sideC < sideA) {
            throw new IllegalArgumentException("Сумма длин двух сторон треугольника должна быть больше "
                    + "длины третьей стороны.");
        }
        this.sideA = sideA;
        this.sideC = sideC;
        this.sideB = sideB;
    }

    @Override
    public double getArea() {
        return 0.5 * sideA * sideC;
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }
}
