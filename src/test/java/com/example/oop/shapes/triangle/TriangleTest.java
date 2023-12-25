package com.example.oop.shapes.triangle;

import com.example.oop.shapes.rectangle.Rectangle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TriangleTest {
    @Test
    public void shouldCorrectCalculateArea() {
        Rectangle rectangle = new Rectangle(2, 4);
        double result = rectangle.getArea();
        double expected = 8;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldCorrectCalculatePerimeter() {
        Rectangle rectangle = new Rectangle(2, 6);
        double result = rectangle.getPerimeter();
        double expected = 16;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void shouldThrowExceptionWhenIllegalSides() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
        {
            Rectangle rectangle = new Rectangle(2, 0);
        });
        assertThat(exception.getMessage()).isEqualTo("Сторона должна быть положительной.");
    }
}