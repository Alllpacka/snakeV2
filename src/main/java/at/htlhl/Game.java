package at.htlhl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Game implements Runnable {

    public final int width;
    public final int height;
    private Board board;
    private Thread gameLoop;
    public boolean gameOver;
    public Snake snake;
    private Point apple;
    private int timeBetweenTicks = 400;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Starts the Game
     */
    public void start() {
        this.gameOver = false;
        Snake.direction = Direction.Right;
        board = new Board(width, height);
        gameLoop = new Thread(this);
        spawnSnake();
        spawnApple();
        gameLoop.start();
    }

    /**
     * Stops the Game
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

    private void spawnApple() {
        apple = getRandomPoint();
        board.setField(apple, Field.APPLE);
    }

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
     * tick tack
     */
    private void tick() {
        snake.move(Snake.direction);
        if (!gameOver) {
            if (snake.isEating(apple)) {
                spawnApple();
                snake.grow();
            }
            board.draw();
        }
    }

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
}