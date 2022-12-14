package at.htlhl;


public class Main {


    /**
     * game Logo
     */
    private static final String logo = """
              .-')        .-') _    ('-.    .-. .-')     ('-.  \s
             ( OO ).     ( OO ) )  ( OO ).-.\\  ( OO )  _(  OO) \s
            (_)---\\_),--./ ,--,'   / . --. /,--. ,--. (,------.\s
            /    _ | |   \\ |  |\\   | \\-.  \\ |  .'   /  |  .---'\s
            \\  :` `. |    \\|  | ).-'-'  |  ||      /,  |  |    \s
             '..`''.)|  .     |/  \\| |_.'  ||     ' _)(|  '--. \s
            .-._)   \\|  |\\    |    |  .-.  ||  .   \\   |  .--' \s
            \\       /|  | \\   |    |  | |  ||  |\\   \\  |  `---.\s
             `-----' `--'  `--'    `--' `--'`--' '--'  `------'
                                         \s""".indent(1);

    /**
     * Prints out the logo, initiates the board and starts the game
     *
     * @param args main method
     */
    public static void main(String[] args) {
        printLogo();
        Game game = new Game(32, 18);
        game.start();
        //
    }

    /**
     * a thread is used to print out the logo,
     * the characters are printed one by one.
     * The printing delay is 10ms.
     */
    public static void printLogo() {
        System.out.println("Copyright (c) 2022 HTL-Hollabrunn\n");
        System.out.println("Made by Brandstetter");
        System.out.println("Made by Franzel");
        System.out.println("Made by Geyser");
        System.out.println("Made by Holzer");
        System.out.println("Made by Zeitlberger\n");

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