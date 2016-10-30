package game.greater_less;

import game.greater_less.exception.ViewException;
import game.greater_less.model.RoundResult;
import game.greater_less.model.ModelStateDTO;

import java.util.List;

import static game.greater_less.model.ModelStateDTO.UNEXPECTED_EMPTY_MODEL_STATE;

/**
 * Created by oleksij.onysymchuk@gmail on 29.10.2016.
 */
public class View {
    public static final String GAME_TITLE = "THE GAME GREATER/LESS.";
    public static final String OBJECTIVE_IS_GUESS_NUMBER_FROM = "Objective is to guess number from ";
    public static final String TO = " to ";
    public static final String PICKED_NUMBER_IS_IN_RANGE_FROM = "The picked number is in range from ";
    public static final String YOUR_TRIES = "Your tries were: ";
    public static final String YOUR_LATEST_RESULT = "Latest try result : ";
    public static final String ENTER_YOUR_NUMBER = "Enter your number or type 'exit' to quit : ";


    public static final String RESULT_GREATER_THAN = "greater than picked number.";
    public static final String RESULT_LESS_THAN = "less than picked number.";
    public static final String RESULT_EQUALS_TO = "YOU GUESSED THE PICKED NUMBER !!!";
    public static final String RESULT_OUT_OF_BOUNDS = "out of current bounds";
    public static final String RESULT_ILLEGAL_INPUT = "illegal number format";

    public static final String TOTAL_TRIES_COUNT = "Your total tries count: ";
    public static final String CORRECT_TRIES_COUNT = "Your correct tries count: ";
    public static final String ILLEGAL_TRIES_COUNT = "Your wrong input tries count: ";
    public static final String GAME_OVER = "GAME OVER.";

    public static final String TRY_LIST_SEPARATOR = ", ";
    public static final String EMPTY_STRING = "";

    public static final String ERROR_HAS_BEEN_OCCURRED = "AN ERROR HAS BEEN OCCURRED : ";
    public static final String PROGRAM_ABNORMALLY_TERMINATED = "PROGRAM HAS BEEN INTERRUPTED DUE TO OCCURRED ERROR.";
    public static final String UNEXPECTED_ROUND_RESULT = "View for obtained roundResult is not implemented. RoundResult = ";

    private void printMessage(String message) {
        System.out.println(message);
    }

    private void printPromt(String prompt) {
        System.out.print(prompt);
    }

    private void printEmptyRow() {
        printMessage(EMPTY_STRING);
    }

    public void showGreeting(int lowerBound, int upperBound) {
        printMessage(GAME_TITLE);
        printEmptyRow();
        printMessage(OBJECTIVE_IS_GUESS_NUMBER_FROM + lowerBound + TO + upperBound);
        printEmptyRow();
    }

    public void showRoundInfo(ModelStateDTO roundInfo) {
        checkForValid(roundInfo);
        printEmptyRow();
        printEmptyRow();
        printMessage(PICKED_NUMBER_IS_IN_RANGE_FROM + roundInfo.getCurrentLowerBound()
                + TO + roundInfo.getCurrentUpperBound());
        printMessage(YOUR_TRIES + convertListToString(roundInfo.getUserTries()));
        printMessage(YOUR_LATEST_RESULT + getStringFromInputCheckResult(roundInfo.getRoundResult()));
    }

    private void checkForValid(ModelStateDTO state) {
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

    public void showPromt() {
        printPromt(ENTER_YOUR_NUMBER);
    }

    public void showStatistics(ModelStateDTO modelState) {
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


    public void showErrorMessage(String errorMessage) {
        printEmptyRow();
        printMessage(PROGRAM_ABNORMALLY_TERMINATED);
        printEmptyRow();
        printMessage(ERROR_HAS_BEEN_OCCURRED + errorMessage);
    }
}
