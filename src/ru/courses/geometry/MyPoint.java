package ru.courses.geometry;

public class MyPoint {
    private double x;
    private double y;

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    void showPoint() {
        System.out.println("x: " + x + " y: " + y);
    }

    //расстояние до другой точки в пространстве
    double distance(MyPoint pEnd) {
        return Math.sqrt((pEnd.x - x) * (pEnd.x - x) + (pEnd.y - y) * (pEnd.y - y));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    @Override
    public String toString() {
        return "MyPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';

    }
}