package at.htlhl;

public class Main {
    public static Game game;

    private static final String logo = "  ____              _        _ \n" +
            " / ___| _ __   __ _| | _____| |\n" +
            " \\___ \\| '_ \\ / _` | |/ / _ \\ |\n" +
            "  ___) | | | | (_| |   <  __/_|\n" +
            " |____/|_| |_|\\__,_|_|\\_\\___(_)\n" +
            "                               ";

    public static void main(String[] args) {
        printLogo();
        Input.startInputListener();
        game = new Game(16, 12);
        game.start();
    }

    public static void printLogo(){
        for (char c : logo.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
    }
}