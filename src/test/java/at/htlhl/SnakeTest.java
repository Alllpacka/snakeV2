package at.htlhl;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    int width = 32;
    int height = 18;

    @Test
    void isEating() {
        Point point = new Point(width / 2, height / 2);
        Board board = new Board(width, height);
        Snake snake = new Snake(new Point(width / 2, height / 2), true, board);
        assertEquals(true, snake.isEating(point));
    }

    @Test
    void putKeyIn_W() {
        Board board = new Board(width, height);
        Snake snake = new Snake(new Point(width / 2, height / 2), true, board);
        snake.putKeyIn(NativeKeyEvent.VC_W);
        assertEquals(Direction.Up, snake.getDirection());
    }
    @Test
    void putKeyIn_A() {
        Board board = new Board(width, height);
        Snake snake = new Snake(new Point(width / 2, height / 2), true, board);
        snake.putKeyIn(NativeKeyEvent.VC_A);
        // Right, because Snake cannot go into itself
        assertEquals(Direction.Right, snake.getDirection());
    }
    @Test
    void putKeyIn_S() {
        Board board = new Board(width, height);
        Snake snake = new Snake(new Point(width / 2, height / 2), true, board);
        snake.putKeyIn(NativeKeyEvent.VC_S);
        assertEquals(Direction.Down, snake.getDirection());
    }
    @Test
    void putKeyIn_D() {
        Board board = new Board(width, height);
        Snake snake = new Snake(new Point(width / 2, height / 2), true, board);
        snake.putKeyIn(NativeKeyEvent.VC_D);
        assertEquals(Direction.Right, snake.getDirection());
    }
    @Test
    void move() {
        Board board = new Board(width, height);
        Snake snake = new Snake(new Point(width / 2, height / 2), true, board);
        snake.move();
    }
}