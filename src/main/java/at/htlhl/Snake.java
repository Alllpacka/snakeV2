package at.htlhl;

import java.util.LinkedList;

public class Snake {

    private LinkedList<Point> body;
    private Point head;

    private Point lastHeadPoint;
    public static Direction direction = Direction.Right;

    private boolean grow;

    public Snake(Point startPoint) {
        body = new LinkedList<>();
        this.head = startPoint;
        Main.game.getBoard().setField(head.getLocation(), Field.HEAD);
    }

    public void move(Direction direction) {
        if (body.size() > 0 && !grow) {
            addBodyPoint();
            removeLastBodyPoint();
        }
        switch (direction) {
            case Up -> {
                Main.game.getBoard().setField(head, Field.EMPTY);
                if (head.getY() - 1 >= 0 && Main.game.getBoard().getFields()[head.getY() - 1][head.getX()] != Field.BODY) {
                    head.setLocation(head.getX(), head.getY() - 1);
                } else {
                    Main.game.gameOver = true;
                }
            }
            case Left -> {
                Main.game.getBoard().setField(head, Field.EMPTY);
                if (this.head.getX() - 1 >= 0  && Main.game.getBoard().getFields()[head.getY()][head.getX() - 1] != Field.BODY) {
                    this.head.setLocation(head.getX() - 1, head.getY());
                } else {
                    Main.game.gameOver = true;
                }
            }
            case Down -> {
                Main.game.getBoard().setField(head, Field.EMPTY);
                if (this.head.getY() + 1 <= Main.game.height-1 && Main.game.getBoard().getFields()[head.getY() + 1][head.getX()] != Field.BODY) {
                    this.head.setLocation(head.getX(), this.head.getY() + 1);
                } else {
                    Main.game.gameOver = true;
                }
            }
            case Right -> {
                Main.game.getBoard().setField(head, Field.EMPTY);
                if (this.head.getX() + 1 <= Main.game.width-1 && Main.game.getBoard().getFields()[head.getY()][head.getX() + 1] != Field.BODY) {
                    this.head.setLocation(this.head.getX() + 1, head.getY());
                } else {
                    Main.game.gameOver = true;
                }
            }
        }

        Main.game.getBoard().setField(head, Field.HEAD);
        for (Point p : getBodyPoints()) {
            Main.game.getBoard().setField(p, Field.BODY);
        }

        lastHeadPoint = new Point(head.getX(), head.getY());
        grow = false;
    }

    public boolean isEating(Point apple) {
        return head.equals(apple);
    }

    /**
     * Grow
     */
    public void grow() {
        grow = true;
        body.add(lastHeadPoint);
    }

    /**
     * getBody (Points)
     * @return Point[]
     */
    public Point[] getBody() {
        Point[] array = new Point[body.size()];
        array = body.toArray(array);
        return array;
    }

    public int getSnakeSize() {
        return getBody().length;
    }

    public Point getHeadPoint(){
        return head;
    }

    public Point[] getBodyPoints() {
        Point[] array = new Point[body.size()];
        array = body.toArray(array);

        return array;
    }

    public void addBodyPoint() {
        body.add(new Point((int) head.getX(), (int) head.getY()));
    }

    public void removeLastBodyPoint() {
        Main.game.getBoard().setField(body.get(0), Field.EMPTY);
        body.remove(0);
    }
}