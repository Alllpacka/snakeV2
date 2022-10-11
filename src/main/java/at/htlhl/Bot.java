package at.htlhl;

public class Bot {

    /**
     * checkBotDirection sets the direction,
     * in which the bot should move to eat an apple
     */
    public Field target;

    public Bot(Snake snake) {
        /*Thread thread = new Thread(() -> {
            for (;true;) {
                checkBotDirection(snake);
            }
        });
        thread.start();*/
    }

    public static void checkBotsDirection() {
        for (Snake snake : Main.game.getSnakes()) {
            if (snake.isBot) {
                snake.bot.checkBotDirection(snake);
            }
        }
    }


    public void checkBotDirection(Snake snake) {
        Point targetPoint;
        /*if (head.getX() >= Main.game.width || head.getY() >= Main.game.width ||
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
        if (Main.game.getBoard().getFields()[head.getY()][head.getX()] == Field.HEAD) {
            throw new RuntimeException("Hit Head");
        }*/



        boolean directionChanged = false;
        int diffX = Main.game.getApple().getX() - snake.getHeadPoint().getX();
        int diffY = Main.game.getApple().getX() - snake.getHeadPoint().getX();


        if (Main.game.getApple().getY() == snake.getHeadPoint().getY()) {
            if (Main.game.getApple().getX() < snake.getHeadPoint().getX()) {
                if (Direction.checkDirection(Direction.Left, snake)) {
                    snake.setDirection(Direction.Left);
                    directionChanged = true;
                }
            } else {
                if (Direction.checkDirection(Direction.Right, snake)) {
                    snake.setDirection(Direction.Right);
                    directionChanged = true;
                }
            }
        }

        if (Main.game.getApple().getY() < snake.getHeadPoint().getY()) {
            if (!directionChanged) {
                if (Direction.checkDirection(Direction.Up, snake)) {
                    snake.setDirection(Direction.Up);
                }
            }
        } else {
            if (!directionChanged) {
                if (Direction.checkDirection(Direction.Down, snake)) {
                    snake.setDirection(Direction.Down);
                }
            }
        }
    }
}