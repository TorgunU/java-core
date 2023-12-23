package com.example.oop;

import com.example.oop.shapes.Shape;
import com.example.oop.shapes.ellipse.Circle;
import com.example.oop.shapes.ellipse.Ellipse;
import com.example.oop.shapes.rectangle.Rectangle;
import com.example.oop.shapes.rectangle.Square;
import com.example.oop.shapes.triangle.RegularTriangle;
import com.example.oop.shapes.triangle.Triangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = createRandomShapes();
        printShapesSum(shapes);
        sumTriangles(shapes);
        sumRectangles(shapes);
    }

    private static List<Shape> createRandomShapes() {
        List<Shape> shapes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            shapes.add(new Ellipse(
                    random.nextDouble(2, 50),
                    random.nextDouble(2, 50))
            );
            shapes.add(new Circle(random.nextDouble(2, 50)));
            shapes.add(new Rectangle(
                    random.nextDouble(2, 50),
                    random.nextDouble(2, 50))
            );
            shapes.add(new Square(random.nextDouble(2, 50)));
            shapes.add(new Triangle(
                    random.nextDouble(2, 50),
                    random.nextDouble(2, 50),
                    random.nextDouble(2, 50)
            ));
            shapes.add(new RegularTriangle(random.nextDouble(2, 50)));
        }
        return shapes;
    }

    private static void printShapesSum(List<Shape> shapes) {
        System.out.println("Сумма всех фигур:");
        for (Shape shape : shapes) {
            System.out.printf("Площадь фигуры: %1$f. Периметр фигуры: %2$f2%n",
                    shape.getArea(),
                    shape.getPerimeter()
            );
        }
    }

    private static void sumShape(String shapeName, List<Shape> shapes) {
        double perimeterSum = 0;
        double areaSum = 0;
        for (Shape shape : shapes) {
            perimeterSum += shape.getPerimeter();
            areaSum += shape.getArea();
        }
        System.out.printf("Сумма периметров всех %s: %.2f. Сумма площадей всех %s: %.2f%n",
                shapeName, perimeterSum, shapeName, areaSum);
    }

    private static void sumTriangles(List<Shape> shapes) {
        List<Shape> triangles = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shape.getClass() == Triangle.class) {
                triangles.add(shape);
            }
        }
        sumShape("Треугольников", triangles);
    }

    private static void sumRectangles(List<Shape> shapes) {
        List<Shape> rectangles = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shape.getClass() == Rectangle.class) {
                rectangles.add(shape);
            }
        }
        sumShape("Прямоугольников", rectangles);
    }
}
