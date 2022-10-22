package at.htlhl;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Das gute Bot
 */
public class Bot {

    /**
     * checkBotDirection sets the direction,
     * in which the bot should move to eat an apple
     */
    public static ArrayList<Snake> snakes = new ArrayList<>();

    public Bot(Snake snake) {
        snakes.add(snake);
        /*Thread thread = new Thread(() -> {
            for (;true;) {
                checkBotDirection(snake);
            }
        });
        thread.start();*/
    }

    /**
     * makes all bots choose direction
     *
     * @param game
     */
    public static void chooseBotsDirection(Game game) {
        for (Snake snake : snakes) {
            if (snake.isBot) {
                snake.bot.chooseDirection(snake, game.getApple(), game.getBoard());
            }
        }
    }

    /**
     *
     * removes directions where next field is not empty from possibly directions
     * choose best direction from possible directions
     *
     * @param snake
     * @param apple
     * @param board
     */

    public void chooseDirection(Snake snake, Point apple, Board board) {
        ArrayList<Direction> possibleDirections = new ArrayList<>();
        //Collections.addAll(possibleDirections, Direction.values());
        addPossibleDirections(possibleDirections, snake.getHeadPoint(), board);

        /*for (Direction direction : possibleDirections) {
            System.out.println("Possible Direction: " + direction);
        }*/

        ArrayList<Direction> goodDirections = new ArrayList<>();
        int differenceX = snake.getHeadPoint().getX() - apple.getX();
        int differenceY = snake.getHeadPoint().getY() - apple.getY();
        if (differenceX < 0) {
            goodDirections.add(Direction.Right);
        } else {
            goodDirections.add(Direction.Left);
        }
        if (differenceY < 0) {
            goodDirections.add(Direction.Down);
        } else {
            goodDirections.add(Direction.Up);
        }

        /*for (Direction direction : goodDirections) {
            System.out.println("Good Direction: " + direction);
        }*/
        /*for (Direction direction : goodDirections) {
            if (direction.isHorizontal()){
                if (differenceX > 0) {

                }
            }
            int difference = direction.isHorizontal() ? differenceX : differenceY;
            if (difference < 0) {
                //if (direction.isGrowingPositively()) {
                    goodDirections.remove(direction);
               // }
            } else if (difference > 0) {
                //if (!direction.isGrowingPositively()) {
                    goodDirections.remove(direction);
                //}
            } else {
                goodDirections.remove(direction);
            }
        }*/

        if (possibleDirections.contains(goodDirections.get(0)) && possibleDirections.contains(goodDirections.get(1))) {
            if (goodDirections.get(0).isHorizontal() && Math.abs(differenceX) > Math.abs(differenceY)){
                snake.setDirection(goodDirections.get(0));
            } else {
                snake.setDirection(goodDirections.get(1));
            }

            /*for (Direction newDirection : goodDirections) {
                if (newDirection.isHorizontal() == Math.abs(differenceX) > Math.abs(differenceY)) {
                    /*if (Direction.checkDirection(newDirection, snake)) {
                        snake.setDirection(newDirection);
                    } else {
                        snake.setDirection(goodDirections.get(1));
                    }
                    snake.setDirection(newDirection);
                }
            }*/
        } else if (possibleDirections.contains(goodDirections.get(0))) {
            snake.setDirection(goodDirections.get(0));
        } else if (possibleDirections.contains(goodDirections.get(1))) {
            snake.setDirection(goodDirections.get(1));
        } else {
            try {
                snake.setDirection(possibleDirections.get(0));
            } catch (IndexOutOfBoundsException e) {

            }
        }
    }

    /**
     * removes directions where next field is not empty
     *
     * @param directions
     * @param point
     * @param board
     */

    /*
    Todo fehlerhaft
     */

    public void addPossibleDirections(ArrayList<Direction> directions, Point point, Board board) {
        for (Direction direction: Direction.values()){
            //System.out.println(direction.getNextField(point, board));
            if (direction.getNextField(point, board).equals(Field.EMPTY) || direction.getNextField(point, board).equals(Field.APPLE)) {
                directions.add(direction);
            }
        }

        // Left, Right, Up, Down
        /*for (int i = 0; i < directions.size(); i++){
            System.out.println(directions.get(i).getNextField(point, board));
            if (!directions.get(i).getNextField(point, board).equals(Field.EMPTY) && !directions.get(i).getNextField(point, board).equals(Field.APPLE)) {
                directions.remove(directions.get(i));
            }
        }*/
    }




}