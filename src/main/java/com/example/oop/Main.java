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
        int createCount = 10;
        List<Shape> shapes = createRandomShapes(createCount);
        sumShapes(shapes);
        sumTriangles(shapes);
        sumRectangles(shapes);
    }

    private static List<Triangle> createRandomTriangles(int count, Random random) {
        List<Triangle> triangles = new ArrayList<>();
        double sideA;
        double sideB;
        double minSideC;
        double maxSideC;
        double sideC;
        for (int i = 0; i < count; i++) {
            sideA = random.nextDouble(2, 50);
            sideB = random.nextDouble(2, 50);
            minSideC = Math.abs(sideA - sideB) + 1;
            maxSideC = sideA + sideB - 1;
            sideC = random.nextDouble(minSideC, maxSideC);
            triangles.add(new Triangle(sideA, sideB, sideC));
        }
        return triangles;
    }

    private static List<RegularTriangle> createRegularTriangle(int count, Random random) {
        List<RegularTriangle> regularTriangles = new ArrayList<>();
        double sideA;
        for (int i = 0; i < count; i++) {
            sideA = random.nextDouble(2, 50);
            regularTriangles.add(new RegularTriangle(sideA));
        }
        return regularTriangles;
    }

    private static List<Ellipse> createRandomEllipses(int count, Random random) {
        List<Ellipse> ellipses = new ArrayList<>();
        double axisX;
        double axisY;
        for (int i = 0; i < count; i++) {
            axisX = random.nextDouble(2, 50);
            axisY = random.nextDouble(2, axisX);
            ellipses.add(new Ellipse(axisX, axisY));
        }
        return ellipses;
    }

    private static List<Circle> createRandomCircles(int count, Random random) {
        List<Circle> circles = new ArrayList<>();
        double radius;
        for (int i = 0; i < count; i++) {
            radius = random.nextDouble(2, 50);
            circles.add(new Circle(radius));
        }
        return circles;
    }

    private static List<Rectangle> createRandomRectangles(int count, Random random) {
        List<Rectangle> rectangles = new ArrayList<>();
        double sideA;
        double sideB;
        for (int i = 0; i < count; i++) {
            sideA = random.nextDouble(2, 50);
            sideB = random.nextDouble(2, 50);
            rectangles.add(new Rectangle(sideA, sideB));
        }
        return rectangles;
    }

    private static List<Square> createRandomSquare(int count, Random random) {
        List<Square> squares = new ArrayList<>();
        double side;
        for (int i = 0; i < count; i++) {
            side = random.nextDouble(2, 50);
            squares.add(new Square(side));
        }
        return squares;
    }

    private static List<Shape> createRandomShapes(int count) {
        Random random = new Random();
        List<Shape> shapes = new ArrayList<>();
        shapes.addAll(createRandomTriangles(count, random));
        shapes.addAll(createRegularTriangle(count, random));
        shapes.addAll(createRandomEllipses(count, random));
        shapes.addAll(createRandomCircles(count, random));
        shapes.addAll(createRandomRectangles(count, random));
        shapes.addAll(createRandomSquare(count, random));
        return shapes;
    }

    private static void sumShapes(List<Shape> shapes) {
        double areaSum = 0;
        double perimeterSum = 0;
        for (Shape shape : shapes) {
            areaSum += shape.getArea();
            perimeterSum += shape.getPerimeter();
        }
        System.out.printf("Сумма периметров: %1$f. Сумма площадей: %2$f%n", perimeterSum, areaSum);
    }

    private static void sumShape(String shapeName, List<Shape> shapes) {
        double perimeterSum = 0;
        double areaSum = 0;
        for (Shape shape : shapes) {
            perimeterSum += shape.getPerimeter();
            areaSum += shape.getArea();
        }
        System.out.printf("Сумма %s. Периметров %.2f. Площадей: %.2f%n",
                shapeName, perimeterSum, areaSum);
    }

    private static void sumTriangles(List<Shape> shapes) {
        List<Shape> triangles = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shape.getClass() == Triangle.class || shape.getClass() == RegularTriangle.class) {
                triangles.add(shape);
            }
        }
        sumShape("Треугольников", triangles);
    }

    private static void sumRectangles(List<Shape> shapes) {
        List<Shape> rectangles = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shape.getClass() == Rectangle.class || shape.getClass() == Square.class) {
                rectangles.add(shape);
            }
        }
        sumShape("Прямоугольников", rectangles);
    }
}
