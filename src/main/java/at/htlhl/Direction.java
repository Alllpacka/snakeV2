package at.htlhl;

public enum Direction {
    Left, Right, Up, Down;


    /**
     * inverts the direction
     * @return Direction
     */
    public Direction invert() {
        return switch (this) {
            case Left -> Direction.Right;
            case Right -> Direction.Left;
            case Up -> Direction.Down;
            case Down -> Direction.Up;
        };
    }


    /**
     * @param direction checks if direction is not the direction of the snake
     * @return boolean
     */
    public static boolean checkDirection(Direction direction, Snake snake) {
        return direction.invert() != snake.getDirection();
    }
}