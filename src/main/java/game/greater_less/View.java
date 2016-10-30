package game.greater_less;

import game.greater_less.exception.ViewException;
import game.greater_less.model.ModelStateSnapshotDTO;
import game.greater_less.model.RoundResult;

import java.util.List;

import static game.greater_less.model.ModelStateSnapshotDTO.UNEXPECTED_EMPTY_MODEL_STATE;

/**
 * This class represents View unit of MVC based architecture of program the game "Greater/Less".
 * It contains text constants for output messages and methods to show certain blocks of game information.
 *
 * @author oleksij.onysymchuk@gmail
 */
public class View {
    public static final String GAME_TITLE = "THE GAME GREATER/LESS.";
    public static final String OBJECTIVE_IS_GUESS_NUMBER_FROM = "Objective is to guess number including bounds from ";
    public static final String TO = " to ";
    public static final String PICKED_NUMBER_IS_IN_RANGE_FROM = "The picked number is in range including bounds from ";
    public static final String YOUR_TRIES = "Your tries were: ";
    public static final String YOUR_LATEST_RESULT = "Latest try result : ";
    public static final String ENTER_YOUR_NUMBER = "Enter your number or type 'exit' to quit : ";

    public static final String RESULT_GREATER_THAN = "Last input value is greater than picked number.";
    public static final String RESULT_LESS_THAN = "Last input value is less than picked number.";
    public static final String RESULT_EQUALS_TO = "YOU HAVE BEEN GUESSED THE PICKED NUMBER !!!";
    public static final String RESULT_OUT_OF_BOUNDS = "Last input value is out of current range";
    public static final String RESULT_ILLEGAL_INPUT = "Last input value is not integer value. You ought to enter integers only!";

    public static final String TOTAL_TRIES_COUNT = "Your total tries count: ";
    public static final String CORRECT_TRIES_COUNT = "Your correct tries count: ";
    public static final String ILLEGAL_TRIES_COUNT = "Your wrong input tries count: ";
    public static final String GAME_OVER = "GAME OVER.";

    public static final String TRY_LIST_SEPARATOR = ", ";
    public static final String EMPTY_STRING = "";

    public static final String ERROR_HAS_BEEN_OCCURRED = "AN ERROR HAS BEEN OCCURRED : ";
    public static final String PROGRAM_ABNORMALLY_TERMINATED = "PROGRAM HAS BEEN INTERRUPTED DUE TO OCCURRED ERROR.";
    public static final String UNEXPECTED_ROUND_RESULT = "View for obtained roundResult is not implemented. RoundResult = ";

    /**
     * Shows on standart out game title and objective
     * @param lowerBound
     *          the value of lower (included) bound of game range
     * @param upperBound
     *          the value of upper (included) bound of game range
     */
    public void showGreeting(int lowerBound, int upperBound) {
        printMessage(GAME_TITLE);
        printEmptyRow();
        printMessage(OBJECTIVE_IS_GUESS_NUMBER_FROM + lowerBound + TO + upperBound);
        printEmptyRow();
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private void printEmptyRow() {
        printMessage(EMPTY_STRING);
    }

    /**
     * Shows on standart out current game range, history of user's input values and result of calculation game round
     * after last user's input value performed to model unit
     *
     * @param roundInfo model state snapshot
     */
    public void showRoundInfo(ModelStateSnapshotDTO roundInfo) {
        checkForValid(roundInfo);
        printEmptyRow();
        printEmptyRow();
        printMessage(PICKED_NUMBER_IS_IN_RANGE_FROM + roundInfo.getCurrentLowerBound()
                + TO + roundInfo.getCurrentUpperBound());
        printMessage(YOUR_TRIES + convertListToString(roundInfo.getUserInputHistory()));
        printMessage(YOUR_LATEST_RESULT + getStringFromInputCheckResult(roundInfo.getRoundResult()));
    }

    private void checkForValid(ModelStateSnapshotDTO state) {
        if ((state == null) || (!state.isValid())) {
            throw new ViewException(UNEXPECTED_EMPTY_MODEL_STATE + (state == null ? null : state.toString()));
        }
    }


    private String convertListToString(List<String> userTries) {
        StringBuffer list = new StringBuffer();
        userTries.forEach(userTry -> list.append(TRY_LIST_SEPARATOR).append(userTry));
        return list.toString().replaceFirst(TRY_LIST_SEPARATOR, EMPTY_STRING);
    }

    private String getStringFromInputCheckResult(RoundResult roundResult) {
        switch (roundResult) {
            case GREATER_THAN_PICKED_NUMBER:
                return RESULT_GREATER_THAN;
            case LESS_THAN_PICKED_NUMBER:
                return RESULT_LESS_THAN;
            case EQUALS_TO_PICKED_NUMBER:
                return RESULT_EQUALS_TO;
            case OUT_OF_BOUNDS:
                return RESULT_OUT_OF_BOUNDS;
            case ILLEGAL_INPUT:
                return RESULT_ILLEGAL_INPUT;
            default:
                throw new UnsupportedOperationException(UNEXPECTED_ROUND_RESULT + roundResult);
        }
    }

    /**
     * Shows on standart out promt to enter next value
     */
    public void showPromt() {
        printPromt(ENTER_YOUR_NUMBER);
    }

    private void printPromt(String prompt) {
        System.out.print(prompt);                                        // do not goes to next line
    }

    /**
     * Shows on standart out user game statistics - different tries counters
     *
     * @param modelState model state snapshot
     */
    public void showStatistics(ModelStateSnapshotDTO modelState) {
        checkForValid(modelState);
        int userIllegalTryCount = modelState.getUserIllegalInputCount();
        int userCorrectTryCount = modelState.getUserCorrectInputCount();

        printEmptyRow();
        printMessage(TOTAL_TRIES_COUNT + (userCorrectTryCount + userIllegalTryCount));
        printMessage(CORRECT_TRIES_COUNT + userCorrectTryCount);
        printMessage(ILLEGAL_TRIES_COUNT + userIllegalTryCount);
        printEmptyRow();
        printMessage(GAME_OVER);
    }

    /**
     * Shows on standart out error massage in case of exception situations
     *
     * @param errorMessage error message to display
     */
    public void showErrorMessage(String errorMessage) {
        printEmptyRow();
        printMessage(PROGRAM_ABNORMALLY_TERMINATED);
        printEmptyRow();
        printMessage(ERROR_HAS_BEEN_OCCURRED + errorMessage);
    }
}
