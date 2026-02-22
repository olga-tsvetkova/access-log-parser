package ru.courses.geometry;

public class Point3D extends MyPoint {
    public Point3D(double x, double y, double z) {
        super (x, y);
        this.z = z;
    }

    private double z;
    public double getZ() {
        return z;
    }
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", z=" + getZ() +
                '}';
    }

    public static class MyPolyLine implements Measurable {

        MyPoint[] pointList;

        public MyPolyLine(MyPoint[] pl) {
            this.pointList = pl;
        }

        //длинна полилинии
        public double getLength() {
            double res= 0;
            for (int i = 0; i < pointList.length - 2; i++) {
                res += pointList[i].distance(pointList[i + 1]);
            }
            return res;
        }

        //массив линий
        public MyLine[] getLines() {
            MyLine[] resLine = new MyLine[pointList.length - 2];
            for (int i = 0; i < pointList.length - 2; i++) {
                resLine[i] = new MyLine(pointList[i],pointList[i + 1]);
            }
            return resLine;
        }

        @Override
        public String toString() {
            String res = "PolyLine ( ";
            for (int i = 0; i < pointList.length - 1; i++) res = res + "{" + pointList[i].getX() + ";" + pointList[i].getY()+ "},";
            if (pointList.length > 0)
                res = res + "{" + pointList[pointList.length - 1].getX() + ";" + pointList[pointList.length - 1].getY()+ "}";
            return res+" )";
        }
    }

    public static class MyPolyLineClose extends MyPolyLine
    {
        public MyPolyLineClose(MyPoint[] pl)
        {
            super(pl);
        }

        @Override
        public double getLength()
        {
            return super.getLength() +  super.pointList[0].distance(super.pointList[super.pointList.length-1]);
        }
    }

    public static class Square {
        private int x; //координата левого верхнего угла по оси X
        private int y; //координата левого верхнего угла оси Y
        private int sideLength; //длина стороны квадрата

        // Конструктор принимает координату левого верхнего угла и длину стороны
        public Square(int x, int y, int sideLength) {
            this.x = x;
            this.y = y;
            setSideLength(sideLength); // Используем метод установки длины стороны для проверки её корректности
        }

        // Метод для изменения координат вершины квадрата
        public void moveTo(int newX, int newY) {
            this.x = newX;
            this.y = newY;
        }

        // Метод для изменения длины стороны квадрата
        public void setSideLength(int length) {
            if (length > 0) { // Проверяем условие положительности длины стороны
                this.sideLength = length;
            } else {
                throw new IllegalArgumentException("Должна быть указана положительная длина.");
            }
        }

        // Геттер для координаты X
        public int getX() {
            return x;
        }

        // Геттер для координаты Y
        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Квадрат в точке (" + x + ", " + y + ") со стороной " + sideLength;
        }
    }
}
