package training.game.greater_less;

import java.util.Scanner;

/**
 * This class represents Controller unit of MVC based architecture of program the game "Greater/Less".
 * It contains user input tool and main game cycle.
 *
 * @version 2.0 05 NOV 2016
 * @author oleksij.onysymchuk@gmail
 */
public class Controller {

    /**
     * Reference to model unit of MVC based architecture of program
     */
    private Model model;
    /**
     * Reference to view unit of MVC based architecture of program
     */
    private View view;

    /**
     * Initiates game's controller unit and set up references to model and view units
     *
     * @param model reference to model unit of application
     * @param view reference to view unit of application
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    Controller() {
    }

    /**
     * runs main game cycle
     */
    public void playGame() {

        String userInputValue;
        int userValue=0;
        Scanner scanner = new Scanner(System.in);

        view.showGreeting();

        while (true) {
            view.showMessage(View.ENTER_BOUNDS_OF_GAME_RANGE);
            int lowerBound = inputIntValue(scanner, View.ENTER_LOWER_BOUND_OF_GAME_RANGE);
            int upperBound = inputIntValue(scanner, View.ENTER_UPPER_BOUND_OF_GAME_RANGE);
            try {
                initModel(lowerBound, upperBound);
                break;
            } catch (RuntimeException e){
                view.showMessage(View.ENTERED_BOUNDS_ARE_INVALID );
            }
        }

        do {
            view.showPromt(model.getLowerBound(), model.getUpperBound());
            userInputValue = inputStringValueWithScanner(scanner);
            if (!checkStringIsInteger(userInputValue)) {
                view.showMessage(View.ILLEGAL_INPUT);
                continue;
            }
            userValue = Integer.parseInt(userInputValue);
            if (!model.isUserInputValueIsInRange(userValue)) {
                view.showMessage(View.RESULT_OUT_OF_BOUNDS);
                continue;
            }
            int result = model.checkUserValue(userValue);
            if (result == Model.GREATER) {
                view.showMessage(View.RESULT_GREATER_THAN);
            } else if (result == Model.EQUALS) {
                view.showYouWinAndStatisitics(model.getUserInputHistory());
            } else {
                view.showMessage(View.RESULT_LESS_THAN);
            }

        } while (userValue != model.getPickedValue());

    }


    /**
     * asks user to enter int value, checks is it int,  in case of int, in other case -
     * shows error and asks again to enter int over and over)
     *
     * @param scanner the reference to the input source
     * @param promptMessage the text of prompt message
     * @return only int value of user's input
     */
    private int inputIntValue(Scanner scanner, String promptMessage) {
        String userInputValue;
        while (true) {
            view.showMessage(promptMessage);
            userInputValue = inputStringValueWithScanner(scanner);
            if (!checkStringIsInteger(userInputValue)) {
                view.showMessage(View.ILLEGAL_INPUT);
                continue;
            }
            return Integer.parseInt(userInputValue);
        }
    }


    /**
     * Sets up initial model values
     *
     * @param lowerBound the value of lower bound of initial game range
     * @param upperBound the value of upper bound of initial game range
     */
    protected void initModel(int lowerBound, int upperBound) {
        model.setGameRange(lowerBound, upperBound);
        model.pickNumber();
    }

    protected String inputStringValueWithScanner(Scanner scanner) {
        return scanner.next();
    }

    /**
     * Checkes if the parameter is correct integer value
     *
     * @param string string value to be checked
     * @return true if the parameter is correct integer value, false in other cases
     */
    protected boolean checkStringIsInteger(String string) {
        try {
            int ignored = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    private int convertStringToInt(String string) {
        return Integer.parseInt(string);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }
}
