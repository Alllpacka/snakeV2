package at.htlhl;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object other){
        if (other == this) {
            return true;
        }
        if (!(other instanceof Point otherPoint)) {
            return false;
        }
        return otherPoint.getX() == this.getX() && otherPoint.getY() == this.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getLocation() {
        return new Point(x,y);
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void increaseYBy(int increment) {
        this.y += increment;
    }

    public void increaseXBy(int increment) {
        this.x += increment;
    }
}