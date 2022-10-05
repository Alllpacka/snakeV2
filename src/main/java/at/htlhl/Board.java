package at.htlhl;

import java.util.Arrays;

public class Board {

    // game-board
    private final Field[][] fields;

    public Board(int width, int height) {
        fields = new Field[height][width];
        // game-board is filled with default EMPTY values
        for (Field[] field : fields) {
            Arrays.fill(field, Field.EMPTY);
        }
    }

    public Field[][] getFields() {
        return fields;
    }

    public void setField(Point point, Field field) {
        fields[point.getY()][point.getX()] = field;
    }

    /**
     * clears the screen, draws game-board and score
     * */
    public void draw() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        System.out.println("Score: " + Main.game.getScore() + " | TimeBetweenTicks: " + Main.game.getTimeBetweenTicks());
        drawLine();
        for (Field[] row : fields) {
            System.out.print("|");
            for (Field field : row) {
                System.out.print(field.translate(field));
                System.out.print(" ");
            }
            System.out.println("|");
        }

        drawLine();
    }

    private void drawLine() {
        System.out.print('+');
        for (int i = 0; i < Main.game.width; i++) {
            System.out.print("--");
        }
        System.out.println('+');
    }
}