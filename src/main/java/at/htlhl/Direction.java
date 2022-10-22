package at.htlhl;

public enum Direction {
    Left, Right, Up, Down;


    /**
     * inverts the direction
     *
     * @return Direction
     */
    public Direction invert() {
        return switch (this) {
            case Left -> Right;
            case Right -> Left;
            case Up -> Down;
            case Down -> Up;
        };
    }

    public boolean isHorizontal() {
        return (this == Left || this == Right);
    }

    public Field getNextField(Point point, Board board) {
        Field[][] fields = board.getFields();
        try {
            return switch (this) {
                case Left -> fields[point.getY()][point.getX() - 1];
                case Right -> fields[point.getY()][point.getX() + 1];
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

    public boolean equals(Direction direction) {
        return this == direction;
    }
}