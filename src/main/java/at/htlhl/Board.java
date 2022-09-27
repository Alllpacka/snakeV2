package at.htlhl;

import java.io.IOException;
import java.util.Arrays;

public class Board {

    private Field[][] fields;

    public Board(int width, int height) {
        fields = new Field[height][width];
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

    public void draw() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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