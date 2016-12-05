package training.game.greater.less;

/**
 * This class is used for initialization and to start the game
 *
 * @version 2.0 05 NOV 2016
 * @author oleksij.onysymchuk@gmail
 */
public class Main {


    /**
     * Initializes all units of MVC architecture and runs the game
     *
     * @param args do not used
     */
    public static void main(String... args) {
        Controller controller = new Controller(new Model(), new View());
        controller.playGame();
    }
}
