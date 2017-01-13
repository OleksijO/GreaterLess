package training.game.greater.less.controller.config;

/**
 * This class represents container for
 *  text constants for output messages of game information.
 *
 * @version 2.0 05 NOV 2016
 * @author oleksij.onysymchuk@gmail
 */
public class ViewMessages {
    public static final String GAME_TITLE = "THE GAME GREATER/LESS.";
    public static final String OBJECTIVE_IS_GUESS_NUMBER_IN_RANGE =
            "Objective of the game is to guess integer, picked (generated) by computer in random way, in  specified game range " +
                    "by step by step attepmts.\n" +
                    "On every try computer tells user if user's input correct number, greater/less than picked number, " +
                    "out of current game range  or equals to picked number.\n" +
                    "After every try current game range becomes smaller and closer to picked value.\n" +
                    "After picked number has been guessed use's tries statistics appears and game is over.";
    public static final String ENTER_BOUNDS_OF_GAME_RANGE =
            "Enter bounds of game range. Upper bound must be greater than lower one. Difference must be more than 2.";
    public static final String RESULT_GREATER_THAN = "Your value is GREATER than picked secret number.";
    public static final String RESULT_LESS_THAN = "Your value is LESS than picked secret number.";
    public static final String RESULT_EQUALS_TO = "CONGRATULATIONS!!! YOU HAVE BEEN GUESSED THE SECRET NUMBER!!! IT WAS %d";
    public static final String RESULT_OUT_OF_BOUNDS = "Your value is OUT OF CURRENT GAME RANGE. Pay more attention! Bounds are also not included.";
    public static final String ILLEGAL_INPUT = "You input value, which is NOT INTEGER value. You ought to enter integers only!";
    public static final String TOTAL_TRIES_COUNT = "Your total tries count: %d";
    public static final String YOU_INPUT_VALUE_HISTORY_IS = "Your attempt values history is: %s";
}
