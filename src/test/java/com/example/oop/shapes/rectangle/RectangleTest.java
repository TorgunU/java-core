package com.example.oop.shapes.rectangle;

import com.example.oop.shapes.triangle.Triangle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleTest {
    @Test
    public void whenTriangleSides012CalculatingAreaThenIllegalArgumentException() {
        Triangle triangle = new Triangle(0, 1, 2);
        Throwable exception = assertThrows(IllegalArgumentException.class, triangle::getArea);
        assertThat(exception.getMessage()).isEqualTo("Сторона должна быть положительной.");
    }

    @Test
    public void whenTriangleSides012CalculatingPerimeterThenIllegalArgumentException() {
        Triangle triangle = new Triangle(0, 1, 2);
        Throwable exception = assertThrows(IllegalArgumentException.class, triangle::getPerimeter);
        assertThat(exception.getMessage()).isEqualTo("Сторона должна быть положительной.");
    }

    @Test
    public void whenTriangleSides1254ThenArea30() {
        Triangle triangle = new Triangle(12, 5, 4);
        double result = triangle.getArea();
        double expected = 30;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenTriangleSides222ThenPerimeter6() {
        Triangle triangle = new Triangle(2, 2, 2);
        double result = triangle.getPerimeter();
        double expected = 6;
        assertThat(result).isEqualTo(expected);
    }
}