package at.htlhl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    int width = 32;
    int height = 18;

    @Test
    void invert() {
        Direction direction = Direction.Right;
        assertEquals(Direction.Left, direction.invert());
        direction = Direction.Left;
        assertEquals(Direction.Right, direction.invert());
        direction = Direction.Up;
        assertEquals(Direction.Down, direction.invert());
        direction = Direction.Down;
        assertEquals(Direction.Up, direction.invert());
    }

    @Test
    void isHorizontal() {
        Direction direction = Direction.Right;
        assertEquals(true, direction.isHorizontal());
        direction = Direction.Left;
        assertEquals(true, direction.isHorizontal());
        direction = Direction.Down;
        assertEquals(false, direction.isHorizontal());
        direction = Direction.Up;
        assertEquals(false, direction.isHorizontal());
    }

    @Test
    void checkDirection() {
        Board board = new Board(width, height);
        Snake snake = new Snake(new Point(width / 2, height / 2), true, board);
        Direction direction = Direction.Right;
        Direction.checkDirection(direction, snake);
        assertEquals(true, direction.checkDirection(direction, snake));
    }
}