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

    public void setX(int x) {
        this.x = Math.min(x, Main.game.width - 1);
    }

    public void setY(int y) {
        this.y = Math.min(y, Main.game.height - 1);
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
}