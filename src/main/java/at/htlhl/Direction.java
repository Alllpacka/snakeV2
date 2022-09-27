package at.htlhl;

public enum Direction {
    Left, Right, Up, Down;


    private static Direction invertDirection(Direction direction) {
        switch (direction) {
            case Left -> {
                return Direction.Right;
            }
            case Right -> {
                return Direction.Left;
            }
            case Up -> {
                return Direction.Down;
            }
            case Down -> {
                return Direction.Up;
            }
        }

        return Direction.Up;
    }

    public static boolean checkDirection(Direction direction) {
        return invertDirection(direction) != Snake.direction;
    }
}