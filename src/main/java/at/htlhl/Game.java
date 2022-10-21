package at.htlhl;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Game implements Runnable {

    public final int width;
    public final int height;

    public final int stoneAmount = 1;
    public final int appleAmount = 1;

    public boolean gameOver;
    private Snake[] snakes;
    private Point apple;

    private int timeBetweenTicks = 400;
    private Board board;
    private Thread gameLoop;
    private long startTime;
    private long stopTime;
    private int timeAlive;

    /**
     * Determines the size of the board through:
     * @param width
     * @param height
     */
    public Game(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * starts the game, starts the timer,
     * Initiates the Board with given values,
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

        for (int i = 0; i < appleAmount; i++) {
            spawnApple();
        }

        for (int i = 0; i < stoneAmount; i++) {
            spawnStone();
        }
        Input.startInputListener(snakes[0]);
        gameLoop.start();
    }

    /**
     * stops the game and asks user if he wants to restart the game, also prints out the Time the Player was alive and the score/apples eaten
     */
    private void gameOver() {
        stopTime = System.nanoTime();
        System.out.println("               ('-.     _   .-')       ('-.                                       (`-.      ('-.  _  .-')   \n" +
                           "              ( OO ).-.( '.( OO )_   _(  OO)                                    _(OO  )_  _(  OO)( \\( -O )  \n" +
                           "  ,----.      / . --. / ,--.   ,--.)(,------.                   .-'),-----. ,--(_/   ,. \\(,------.,------.  \n" +
                           " '  .-./-')   | \\-.  \\  |   `.'   |  |  .---'                  ( OO'  .-.  '\\   \\   /(__/ |  .---'|   /`. ' \n" +
                           " |  |_( O- ).-'-'  |  | |         |  |  |                      /   |  | |  | \\   \\ /   /  |  |    |  /  | | \n" +
                           " |  | .--, \\ \\| |_.'  | |  |'.'|  | (|  '--.                   \\_) |  |\\|  |  \\   '   /, (|  '--. |  |_.' | \n" +
                           "(|  | '. (_/  |  .-.  | |  |   |  |  |  .--'                     \\ |  | |  |   \\     /__) |  .--' |  .  '.' \n" +
                           " |  '--'  |   |  | |  | |  |   |  |  |  `---.                     `'  '-'  '    \\   /     |  `---.|  |\\  \\  \n" +
                           "  `------'    `--' `--' `--'   `--'  `------'                       `-----'      `-'      `------'`--' '--' \n" +
                           "\n");

        timeAlive = (int) ((stopTime - startTime) / Math.pow(10, 9));
        System.out.println("\t" + "Time alive: " + timeAlive + "s");
        for (Snake snake : snakes) {
            System.out.println("\t" + "Apples eaten: " + snake.getScore() + " - isBot: " + snake.isBot + "\n");
        }

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
            } else if (input.equalsIgnoreCase("y") || input.equals("") || input.equalsIgnoreCase("yes")) {
                Game newGame = new Game(32, 18);
                newGame.start();
            }
        } while (!input.equalsIgnoreCase("y") && !input.equals(""));
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Spawns both snakes, one Player-snake, one Bot-snake
     */
    private void spawnSnake() {
        this.snakes = new Snake[2];
        this.snakes[0] = new Snake(new Point(width / 2, height / 2), false, board);
        this.snakes[1] = new Snake(new Point(width / 2 - 2, height / 2 - 2), true, board);
    }

    /**
     * spawns apple on random position
     */
    protected void spawnApple() {
        apple = generateRandomPoint();
        board.setField(apple, Field.APPLE);
    }

    /**
     * spawns stone on random position
     */
    private void spawnStone() {
        board.setField(generateRandomPoint(), Field.STONE);
    }

    /**
     * generates a random Point (used for apple spawn and stone spawn)
     *
     * @return Point
     */
    private Point generateRandomPoint() {
        Point point = new Point((int) (width * Math.random()), (int) (height * Math.random()));
        for (Snake value : snakes) {
            for (Point p : value.getBody()) {
                if (p.equals(point)) {
                    return generateRandomPoint();
                }
            }
        }
        for (Snake snake : snakes) {
            if (snake.getHeadPoint().equals(point)) {
                return generateRandomPoint();
            }
        }
        if (board.getFields()[point.getY()][point.getX()] != Field.EMPTY) {
            return generateRandomPoint();
        }
        return point;
    }

    /**
     * performs every tick, when reached in game-loop,
     * moves snake and if an apple is eaten, a new apple
     * is spawned, score is increased
     */
    private void tick() {
        for (Snake snake : snakes) {
            if (!snake.move()) {
                gameOver = true;
            }
        }
        Bot.chooseBotsDirection(this);
        if (!gameOver) {
            for (Snake snake : snakes) {
                if (snake.isEating(apple)) {
                    snake.setScore(snake.getScore()+1);
                    spawnApple();
                    snake.grow();
                    timeBetweenTicks = Math.max((int) (timeBetweenTicks * 0.96), 100);
                    spawnStone();
                    spawnStone();
                    spawnStone();
                    spawnStone();
                    spawnStone();
                    /*switch (timeBetweenTicks) {
                        case 100, 101 -> spawnStone();
                    }
                     */
                }
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