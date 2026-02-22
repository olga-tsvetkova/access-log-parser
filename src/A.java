public class A {
    private int x;

    public  A(int x){
        privetSetX(x);
    }
//возвращает х
    public int getX() {
        return x;
    };

    private void privetSetX(int x) {
        if (x<0)
            throw new IllegalArgumentException("x is negative");
        this.x = x;
    }
//устанавливает х, если он больше нуля.
//Если меньше или равно нулю, то ничего не делает.
    public void setX(int x) {
        privetSetX(x);
    }

    @Override
    public String toString() {
        return "A{" + "x=" + x + '}';
    }
}
