package at.htlhl;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Game implements Runnable {

    public final int width;
    public final int height;

    public boolean gameOver;
    private Snake[] snakes;

    private Point apple;
    private int timeBetweenTicks = 400;
    private Board board;
    private Thread gameLoop;

    private int score;
    private long startTime;
    private long stopTime;
    private int timeAlive;

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
        startTime = System.nanoTime();
        this.gameOver = false;
        board = new Board(width, height);
        gameLoop = new Thread(this);
        spawnSnake();
        snakes[0].setDirection(Direction.Right);
        snakes[1].setDirection(Direction.Right);
        spawnApple();
        gameLoop.start();
    }

    /**
     * stops the game and asks user if
     * he wants to restart the game
     */
    private void gameOver() {
        stopTime = System.nanoTime();
        System.out.println("   ____                         ___                 _ \n" +
                "  / ___| __ _ _ __ ___   ___   / _ \\__   _____ _ __| |\n" +
                " | |  _ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__| |\n" +
                " | |_| | (_| | | | | | |  __/ | |_| |\\ V /  __/ |  |_|\n" +
                "  \\____|\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|  (_)\n" +
                "                                                      ");

        timeAlive = (int)((stopTime - startTime)/Math.pow(10, 9));
        System.out.println("Time alive: " + timeAlive + "s");

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
        this.snakes = new Snake[2];
        this.snakes[0] = new Snake(new Point(width / 2, height / 2), false);
        this.snakes[1] = new Snake(new Point(width / 2 - 2, height / 2 - 2), true);
    }

    /**
     * spawns apple on random position
     */
    private void spawnApple() {
        apple = generateRandomPoint();
        board.setField(apple, Field.APPLE);
    }

    /**
     * generates a random Point (used for apple spawn)
     *
     * @return Point
     */
    private Point generateRandomPoint() {
        Point point = new Point((int) (width * Math.random()), (int) (height * Math.random()));
        for (int i = 0; i < snakes.length; i++) {
            for (Point p : snakes[i].getBody()) {
                if (p.equals(point)) {
                    return generateRandomPoint();
                }
            }
        }
        for (int i = 0; i < snakes.length; i++) {
            if (snakes[i].getHeadPoint().equals(point)) {
                return generateRandomPoint();
            }
        }
        return point;
    }

    /**
     * performs every tick, when reached in game-loop,
     * moves snake and if an apple is eaten, a new apple
     * is spawned, score is increased
     */
    private void tick() {
        Bot.checkBotsDirection();
        for (int i = 0; i < snakes.length; i++) {
            snakes[i].move(snakes[i].getDirection());
        }
        if (!gameOver) {
            for (int i = 0; i < snakes.length; i++) {
                if (snakes[i].isEating(apple)) {
                    score++;
                    spawnApple();
                    snakes[i].grow();
                    timeBetweenTicks = Math.max((int) (timeBetweenTicks * 0.96), 100);
                }
                board.draw();
            }
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

    public Snake[] getSnakes() {
        return snakes;
    }

    public Point getApple() {
        return apple;
    }
}