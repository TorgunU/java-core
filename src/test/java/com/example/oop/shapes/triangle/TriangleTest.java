package com.example.oop.shapes.triangle;

import com.example.oop.shapes.rectangle.Rectangle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TriangleTest {
    @Test
    public void whenRectangleSides22ThenArea8() {
        Rectangle rectangle = new Rectangle(2, 4);
        double result = rectangle.getArea();
        double expected = 8;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenRectangleSides26ThenPerimeter8() {
        Rectangle rectangle = new Rectangle(2, 6);
        double result = rectangle.getPerimeter();
        double expected = 16;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenRectangleSides20CalculatingAreaThenIllegalArgumentException() {
        Rectangle rectangle = new Rectangle(2, 0);
        Throwable exception = assertThrows(IllegalArgumentException.class, rectangle::getArea);
        assertThat(exception.getMessage()).isEqualTo("Сторона Треугольника должна быть положительной.");
    }

    @Test
    public void whenRectangleSides20CalculatingPerimeterThenIllegalArgumentException() {
        Rectangle rectangle = new Rectangle(2, 0);
        Throwable exception = assertThrows(IllegalArgumentException.class, rectangle::getPerimeter);
        assertThat(exception.getMessage()).isEqualTo("Сторона Треугольника должна быть положительной.");
    }
}