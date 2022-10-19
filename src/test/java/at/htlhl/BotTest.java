package at.htlhl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {

    // NOTE: Main.game static Implementation not suitable for testing

    int width = 32;
    int height = 18;


    @Test
    void chooseDirection() {
        Point point = new Point(8, 8);
        Board board = new Board(width, height);
        Snake snake = new Snake(new Point(width / 2, height / 2), true, board);
        Bot bot = new Bot(snake);
        bot.chooseDirection(snake, point, board);
        assertEquals(Direction.Right, snake.getDirection());
    }
}