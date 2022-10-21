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
        System.out.println("Point: " + point.getY() + " : " + point.getX());
        try {
            switch (this) {
                case Left -> {
                    System.out.println(point.getY() + " : " + (point.getX() - 1));
                    return fields[point.getY()][point.getX() - 1];
                }
                case Right -> {
                    System.out.println(point.getY() + " : " + (point.getX() + 1));
                    return fields[point.getY()][point.getX() + 1];
                }
                case Up -> {
                    System.out.println((point.getY() - 1) + " : " + (point.getX()));
                    return fields[point.getY() - 1][point.getX()];
                }
                case Down -> {
                    System.out.println((point.getY() + 1) + " : " + (point.getX()));
                    return fields[point.getY() + 1][point.getX()];
                }
                default -> {
                    return null;
                }
            }
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