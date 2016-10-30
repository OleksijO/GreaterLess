package game.greater_less;

import game.greater_less.model.Model;

/**
 * This class is used for initialization and to start the game
 *
 * @author oleksij.onysymchuk@gmail
 */
public class Main {


    /**
     * Initializes all units of MVC architecture and runs the game
     * @param args does not used
     */
    public static void main(String... args) {
        Controller controller = new Controller(new Model(), new View());
        controller.playGame();
    }
}
