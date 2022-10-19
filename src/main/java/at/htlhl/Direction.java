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

    public boolean isHorizontal() {
        return (this == Left || this == Right);
    }

    public Field getNextField(Point point, Board board) {
        Field[][] fields = board.getFields();
        try {
            return switch (this) {
                case Right -> fields[point.getY()][point.getX() + 1];
                case Left -> fields[point.getY()][point.getX() - 1];
                case Up -> fields[point.getY() - 1][point.getX()];
                case Down -> fields[point.getY() + 1][point.getX()];
            };
        } catch (IndexOutOfBoundsException e) {
            return Field.STONE;
        }
    }

    /**
     * @param direction checks if direction is not the direction of the snake
     * @return boolean
     */
    public static boolean checkDirection(Direction direction, Snake snake) {
        return direction.invert() != snake.getDirection();
    }
}