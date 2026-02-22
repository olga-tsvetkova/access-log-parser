public class MyLine implements Measurable
{
    MyPoint start;
    MyPoint end;

    public MyLine(MyPoint startPoint, MyPoint endPoint) {
        start =  new MyPoint(startPoint.getX(),startPoint.getY());
        end =    new MyPoint(endPoint.getX(),endPoint.getY());;
    }

    public MyLine(int startX,  int startY, int endX, int endY) {
        this.start = new MyPoint(startX, startY);
        this.end   = new MyPoint(endX, endY);
    }

    public double getLength() {return end.distance(start);}

    void  showLine() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "MyLine { start(" + start.getX() + ", " + start.getY() + ")" +
                ", end( " + end.getX() + ", " + end.getY() + ")}";
    }

}
