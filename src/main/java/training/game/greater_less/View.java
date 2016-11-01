package training.game.greater_less;

import java.util.List;

/**
 * This class represents View unit of MVC based architecture of program the game "Greater/Less".
 * It contains text constants for output messages and methods to show certain blocks of game information.
 *
 * @author oleksij.onysymchuk@gmail
 */
public class View {
    public static final String GAME_TITLE = "THE GAME GREATER/LESS.";
    public static final String OBJECTIVE_IS_GUESS_NUMBER_IN_RANGE =
            "Objective of the game is to guess integer, picked (generated) by computer in random way, in  specified game range " +
                    "by step by step attepmts.\n" +
                    "On every try computer tells user if user's input correct number, greater/less than picked number, " +
                    "out of current game range  or equals to picked number.\n" +
                    "After every try current game range becomes smaller and closer to picked value.\n" +
                    "After picked number has been guessed use's tries statistics appears and game is over.";
    public static final String TO = " to ";
    public static final String ENTER_INT_NUMBER_IN_RANGE_FROM = "Enter INT number in range from ";
    public static final String RESULT_GREATER_THAN = "Your value is GREATER than picked secret number.";
    public static final String RESULT_LESS_THAN = "Your value is LESS than picked secret number.";
    public static final String RESULT_EQUALS_TO = "CONGRATULATIONS!!! YOU HAVE BEEN GUESSED THE SECRET NUMBER = ";
    public static final String RESULT_OUT_OF_BOUNDS = "Your value is OUT OF CURRENT GAME RANGE. Pay more attention! Bounds are also not included.";
    public static final String ILLEGAL_INPUT = "You input value, which is NOT INTEGER value. You ought to enter integers only!";
    public static final String TOTAL_TRIES_COUNT = "Your total tries count: ";
    public static final String YOU_INPUT_VALUE_HISTORY_IS = "Your input value history is: ";
    public static final String GAME_OVER = "GAME OVER.";

    public static final String HISTORY_LIST_SEPARATOR = ", ";
    public static final String EMPTY_STRING = "";


    /**
     * Shows on standart out game title and objective
     */
    public void showGreeting() {
        printMessage(GAME_TITLE);
        printMessage(EMPTY_STRING);
        printMessage(OBJECTIVE_IS_GUESS_NUMBER_IN_RANGE);
        printMessage(EMPTY_STRING);
    }

    /**
     * Shows on standart out promt to enter next value
     */
    public void showPromt(int lowerBound, int upperBound) {
        printMessage(ENTER_INT_NUMBER_IN_RANGE_FROM, lowerBound, TO, upperBound);
    }

    /**
     * Shows specified message on standart out
     *
     * @param message
     */
    public void showMessage(String message){
        printMessage(message);
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private void printMessage(Object... list) {
        StringBuilder message = new StringBuilder();
        for (Object obj : list) {
            message.append(obj.toString());
        }
        printMessage(message.toString());
    }

    private String convertListOfIntegerToString(List<Integer> userTries) {
        StringBuffer list = new StringBuffer();
        userTries.forEach(userTry -> list.append(HISTORY_LIST_SEPARATOR).append(userTry));
        return list.toString().replaceFirst(HISTORY_LIST_SEPARATOR, EMPTY_STRING);
    }

    /**
     * Shows on standart out user game statistics - different tries counters
     *
     * @param history the list of user attempts
     */
    public void showYouWinAndStatisitics(List<Integer> history) {
        printMessage(RESULT_EQUALS_TO,history.get(history.size()-1));
        printMessage(EMPTY_STRING);
        printMessage(TOTAL_TRIES_COUNT, history.size());
        printMessage(YOU_INPUT_VALUE_HISTORY_IS, convertListOfIntegerToString(history));
        printMessage(EMPTY_STRING);
        printMessage(GAME_OVER);
    }

}
