package at.htlhl;

public class Main {
    public static Game game;

    public static void main(String[] args) {
        Input.startInputListener();
        game = new Game(16, 12);
        game.start();

        //Hallo, das ist unser Programm: Women simulator: Get all the coins
        //ViEl sPaß!

        while (true) {
        }
    }
}