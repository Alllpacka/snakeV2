package at.htlhl;

import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private ArrayList<Point> body;
    private Point head;

    private Point lastHeadPoint;
    private Direction direction;

    public Snake() {

    }

    public void move() {
        if (body.size() > 0 && !grow) {
            addBodyPoint();
            removeLastBodyPoint();
        }

        switch (direction) {
            case Up -> {
                Main.game.area.setField(head, Field.EMPTY);
                if (head.getY() - 1 >= 0) {
                    head.setLocation(head.getX(), head.getY() - 1);
                } else {
                    gameOver();
                }
            }
            case Left -> {
                Main.game.area.setField(head, Field.EMPTY);
                if (this.head.getX() - 1 >= 0) {
                    this.head.setLocation(head.getX() - 1, head.getY());
                } else {
                    gameOver();
                }
            }
            case Down -> {
                Main.game.area.setField(head, Field.EMPTY);
                if (this.head.getY() + 1 <= Main.game.height-1) {
                    this.head.setLocation(head.getX(), this.head.getY() + 1);
                } else {
                    gameOver();
                }
            }
            case Right -> {
                Main.game.area.setField(head, Field.EMPTY);
                if (this.head.getX() + 1 <= Main.game.width-1) {
                    this.head.setLocation(this.head.getX() + 1, head.getY());
                } else {
                    gameOver();
                }
            }
        }

        lastHeadPoint = new Point((int) head.getX(), (int) head.getY());
        grow = false;
    }

    public void grow() {

    }

    public void changeDirection() {

    }
}
