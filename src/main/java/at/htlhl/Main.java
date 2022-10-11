package at.htlhl;

public class Main {
    public static Game game;


    /**
     * game Logo
     */
    private static final String logo = """
             ____              _        _\s
            / ___| _ __   __ _| | _____| |
            \\___ \\| '_ \\ / _` | |/ / _ \\ |
             ___) | | | | (_| |   <  __/_|
            |____/|_| |_|\\__,_|_|\\_\\___(_)
                                         \s""".indent(1);

    /**
     * @param args
     * main method
     */
    public static void main(String[] args) {
        printLogo();
        Input.startInputListener();
        game = new Game(32, 18);
        game.start();
    }

    /**
     * a thread is used to print out the logo,
     * the characters are printed one by one.
     * The printing delay is 10ms.
     */
    public static void printLogo() {
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