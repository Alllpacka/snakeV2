package at.htlhl;

import java.util.LinkedList;

public class Snake {

    private final LinkedList<Point> body;
    private final Point head;

    private Point lastHeadPoint;
    private static Direction direction = Direction.Right;

    private boolean grow;

    public Snake(Point startPoint) {
        body = new LinkedList<>();
        this.head = startPoint;
        Main.game.getBoard().setField(head.getLocation(), Field.HEAD);
    }

    /**
     * @param direction moves the snake head position on board according to direction,
     *                  and checks game-over
     */
    public void move(Direction direction) {
        if (body.size() > 0 && !grow) {
            addBodyPoint();
            removeLastBodyPoint();
        }

        try {
            Main.game.getBoard().setField(head, Field.EMPTY);
            goDirection();

            Main.game.getBoard().setField(head, Field.HEAD);
            for (Point p : body) {
                Main.game.getBoard().setField(p, Field.BODY);
            }

            lastHeadPoint = new Point(head.getX(), head.getY());
            grow = false;
        } catch (RuntimeException e) {
            Main.game.gameOver = true;
        }
    }

    private void goDirection() {
        if (head.getX() >= Main.game.width || head.getY() >= Main.game.width ||
                head.getX() < 0 || head.getY() < 0) {
            throw new IndexOutOfBoundsException("Out of Field");
        }
        int xDiff = 0;
        int yDiff = 0;
        switch (direction) {
            case Left -> {
                head.increaseXBy(-1);
            }
            case Right -> {
                head.increaseXBy(1);
            }
            case Up -> {
                head.increaseYBy(-1);
            }
            case Down -> {
                head.increaseYBy(1);
            }
        }
        if (Main.game.getBoard().getFields()[head.getY()][head.getX()] == Field.BODY) {
            throw new RuntimeException("Hit Body");
        }
    }

    /**
     * @param apple checks if head-point is on apple-point
     * @return
     */
    public boolean isEating(Point apple) {
        return head.equals(apple);
    }

    /**
     * snake grow, add new body-point
     */
    public void grow() {
        grow = true;
        body.add(lastHeadPoint);
    }

    public Point[] getBody() {
        Point[] array = new Point[body.size()];
        array = body.toArray(array);
        return array;
    }

    public int getSnakeSize() {
        return getBody().length;
    }

    public Point getHeadPoint() {
        return head;
    }

    public Point[] getBodyPoints() {
        Point[] array = new Point[body.size()];
        array = body.toArray(array);

        return array;
    }

    /**
     * adds new body-point
     */
    private void addBodyPoint() {
        body.add(new Point((int) head.getX(), (int) head.getY()));
    }

    /**
     * removes last body-point
     */
    private void removeLastBodyPoint() {
        Main.game.getBoard().setField(body.get(0), Field.EMPTY);
        body.remove(0);
    }

    public static Direction getDirection() {
        return direction;
    }

    public static void setDirection(Direction newDirection) {
        if (newDirection.invert() != direction) {
            direction = newDirection;
        }
    }
}