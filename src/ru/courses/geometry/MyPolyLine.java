package ru.courses.geometry;

public class MyPolyLine implements Measurable {

   private MyPoint[] pointList;

   public MyPoint[] getPointList() {
        return this.pointList.clone();
    }

    public MyPolyLine(MyPoint[] pl) {
        this.pointList = pl.clone();
    }

 //длина линии
    public double getLength() {
        double res=0;
        for(int i=0;i<this.pointList.length-1;i++){
            res+=this.pointList[i].distance(this.pointList[i+1]);
        }
        return res;
    }

 //массив линий
   public MyLine[] getLines(){
       MyLine[] reslines = new MyLine[pointList.length - 2];
       for (int i = 0; i < pointList.length - 2; i++) {
           reslines[i] = new MyLine(pointList[i], pointList[i + 1]);
       }
       return reslines;
   }

    @Override
    public String toString() {
        String res = "MyPolyLine (";
        for (int i = 0; i < pointList.length; i++) res = res +"{"+pointList[i].getX()+","+pointList[i].getY()+"}";
        if (pointList.length > 0)
            res = res + "{"+ pointList[pointList.length-1].getX()+","+pointList[pointList.length-1].getY()+"}";
        return res+")";
    }
}



