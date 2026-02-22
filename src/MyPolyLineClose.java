public class MyPolyLineClose extends MyPolyLine {
    public MyPolyLineClose( MyPoint[] pl){
        super(pl);
    }

    @Override
    public double getLength(){
        MyPoint[] pl = getPointList();
        return super.getLength() + pl[0].distance(pl[pl.length -1]); // длина линии + расстояние между первой и последней точкой
    }
}
