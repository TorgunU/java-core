package com.example.oop.shapes.rectangle;

import com.example.oop.shapes.triangle.Triangle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleTest {
    @Test
    public void shouldCorrectCalculateArea() {
        Triangle triangle = new Triangle(4, 5, 4);
        double result = triangle.getArea();
        double expected = 8;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldCorrectCalculatePerimeter() {
        Triangle triangle = new Triangle(2, 2, 2);
        double result = triangle.getPerimeter();
        double expected = 6;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldThrowExceptionWhenIllegalSides() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
        {
            Triangle triangle = new Triangle(0, 1, 2);
        });
        assertThat(exception.getMessage()).isEqualTo("Сторона должна быть положительной.");
    }

    @Test
    public void shouldThrowExceptionWhenSumOfTwoSidesLowerThird() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
        {
            Triangle triangle = new Triangle(1, 1, 5);
        });
        assertThat(exception.getMessage()).isEqualTo("Сумма длин двух сторон треугольника должна быть " +
                "больше длины третьей стороны.");
    }
}