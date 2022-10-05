package at.htlhl;

public class Test {

    public static boolean bot = true;

    public static void checkBotDirection() {
        boolean directionChanged = false;
        if (Main.game.getApple().getY() == Main.game.getSnake().getHeadPoint().getY()) {
            if (Main.game.getApple().getX() < Main.game.getSnake().getHeadPoint().getX()) {
                if (Direction.checkDirection(Direction.Left)) {
                    Snake.setDirection(Direction.Left);
                    directionChanged = true;
                }
            } else {
                if (Direction.checkDirection(Direction.Right)) {
                    Snake.setDirection(Direction.Right);
                    directionChanged = true;
                }
            }
        }

        if (Main.game.getApple().getY() < Main.game.getSnake().getHeadPoint().getY()) {
            if (!directionChanged) {
                if (Direction.checkDirection(Direction.Up)) {
                    Snake.setDirection(Direction.Up);
                }
            }
        } else {
            if (!directionChanged) {
                if (Direction.checkDirection(Direction.Down)) {
                    Snake.setDirection(Direction.Down);
                }
            }
        }
    }
}