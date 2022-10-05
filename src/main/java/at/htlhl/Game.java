package at.htlhl;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Game implements Runnable {

    public final int width;
    public final int height;

    public boolean gameOver;
    public Snake snake;

    private Point apple;
    private int timeBetweenTicks = 400;
    private Board board;
    private Thread gameLoop;

    private int score;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * starts the game, sets default values,
     * creates spawns snake and apple,
     * start game-loop
     */
    public void start() {
        this.gameOver = false;
        Snake.setDirection(Direction.Right);
        board = new Board(width, height);
        gameLoop = new Thread(this);
        spawnSnake();
        spawnApple();
        gameLoop.start();
    }

    /**
     * stops the game and asks user if
     * he wants to restart the game
     */
    private void gameOver() {
        System.out.println("   ____                         ___                 _ \n" +
                "  / ___| __ _ _ __ ___   ___   / _ \\__   _____ _ __| |\n" +
                " | |  _ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__| |\n" +
                " | |_| | (_| | | | | | |  __/ | |_| |\\ V /  __/ |  |_|\n" +
                "  \\____|\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|  (_)\n" +
                "                                                      ");
        var scan = new java.util.Scanner(System.in);
        String input = "";
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
        } catch (AWTException e) {
            System.out.println(e.getMessage());
        }
        do {
            System.out.println("Neustarten (Y/n)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                System.exit(0);
            } else if (input.equalsIgnoreCase("y") || input.equals("")) {
                Main.game = new Game(16, 12);
                Main.game.start();
            }
        } while (!input.equalsIgnoreCase("y") && !input.equals(""));
    }

    public Board getBoard() {
        return board;
    }

    private void spawnSnake() {
        this.snake = new Snake(new Point(width / 2, height / 2));
    }

    /**
     * spawns apple on random position
     */
    private void spawnApple() {
        apple = getRandomPoint();
        board.setField(apple, Field.APPLE);
    }

    /**
     * generates a random Point (used for apple spawn)
     *
     * @return Point
     */
    private Point getRandomPoint() {
        Point point = new Point((int) (width * Math.random()), (int) (height * Math.random()));
        for (Point p : snake.getBody()) {
            if (p.equals(point)) {
                return getRandomPoint();
            }
        }
        if (snake.getHeadPoint().equals(point)) {
            return getRandomPoint();
        }
        return point;
    }

    /**
     * performs every tick, when reached in game-loop,
     * moves snake and if an apple is eaten, a new apple
     * is spawned, score is increased
     */
    private void tick() {
        if (Test.bot) {
            Test.checkBotDirection();
        }
        snake.move(Snake.getDirection());
        if (!gameOver) {
            if (snake.isEating(apple)) {
                score++;
                spawnApple();
                snake.grow();
                timeBetweenTicks = Math.max((int) (timeBetweenTicks * 0.96), 100);
            }
            board.draw();
        }
    }

    /**
     * game loop runs, while not game-over
     */
    @Override
    public void run() {
        while (!gameOver) {
            try {
                tick();
                Thread.sleep(timeBetweenTicks);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        gameOver();
    }

    public int getScore() {
        return score;
    }

    public int getTimeBetweenTicks() {
        return timeBetweenTicks;
    }

    public Snake getSnake() {
        return snake;
    }

    public Point getApple() {
        return apple;
    }
}