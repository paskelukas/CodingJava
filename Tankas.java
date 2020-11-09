public class Tankas {
    int x, y, puse, greitis;
    boolean gyvas =true;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPuse() {
        return puse;
    }

    public void setPuse(int puse) {
        this.puse = puse;
    }

    public int getGreitis() {
        return greitis;
    }

    public void setGreitis(int greitis) {
        this.greitis = greitis;
    }

    Tankas(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
}