package ru.gb.lesson1.task3;

import java.util.Arrays;
import java.util.List;

public class ShapeApp {
    public static void main(String[] args) {
        List<Shape> shapes = Arrays.asList(
                new Square(4),
                new Circle(2),
                new Triangle(2, 4));
        System.out.println(getSumArea(shapes));
    }

    public static double getSumArea(List<Shape> shapes) {
        double res = 0;
        for (Shape shape : shapes) {
            res = res + shape.getArea();
        }
        return res;
    }
}
