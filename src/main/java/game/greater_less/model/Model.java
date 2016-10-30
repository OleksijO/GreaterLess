package game.greater_less.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static game.greater_less.model.RoundResult.*;

/**
 * This class represents Model unit of MVC based architecture of program the game "Greater/Less".
 * It contains game logic and state.
 * Objective of the game is to guess integer, picked (generated) by computer in random way, in game range
 * by step by step tries.
 * On every try computer tells user if user's input correct number, greater/less than picked number,
 * out of current game range  or equals to picked number. After every try current game range becomes smaller
 * and closer to picked value.
 * After picked number has been guessed use's tries statistics appear and game is over.
 *
 * @author oleksij.onysymchuk@gmail
 */
public class Model {
    /* The value of lower bound (included) of initial game range*/
    public static final int LOWER_BOUND = 0;
    /* The value of upper bound (included) of initial game range*/
    public static final int UPPER_BOUND = 100;

    /* The picked by computer randome value in initial game range, which user has to guess */
    private int pickedNumber;
    /* The value of current (in-game) lower bound (included) of initial game range */
    private int currentLowerBound;
    /* The value of current (in-game) lower bound (included) of initial game range */
    private int currentUpperBound;
    /* Container for storing user try guess inputs, i.e. user inputs history */
    private List<String> userInputs;
    /* The result of last round of game on user try input */
    private RoundResult roundResult;

    /* Counter for illegal user inputs - non numbers and out of range. The counter is used for after game statistics  */
    private int userIllegalInputCount;
    /* Counter for numeric user inputs. The counter is used for after game statistics  */
    private int userCorrectInputCount;
    /* this value determines including or not non number inputs to container of user tries and game statistics  */
    private boolean includeIllegalNonIntegerInputsToHistory = true;

    /**
     * Initiates game's model unit
     */
    public Model() {
        init();
    }

    /**
     * Initializes fields with configured by constants values
     */
    private void init() {
        pickedNumber = (int) (LOWER_BOUND + Math.random() * (UPPER_BOUND - LOWER_BOUND + 1));
        currentLowerBound = LOWER_BOUND;
        currentUpperBound = UPPER_BOUND;
        userInputs = new ArrayList<>();
        userIllegalInputCount = 0;
    }

    /**
     * Calculates result of the game round after user has been input some value ({@code userInput})
     * and updates model state
     *
     * @param userInput The user's input value
     */
    public void performRoundWithUserInput(String userInput) {

        if (!checkStringIsInteger(userInput)) {
            if (includeIllegalNonIntegerInputsToHistory) {
                userIllegalInputCount++;
                userInputs.add(userInput);
            }
            roundResult = ILLEGAL_INPUT;
            return;
        }

        int userInputNumber = Integer.parseInt(userInput);
        userInputs.add(userInput);
        userCorrectInputCount++;
        if ((userInputNumber > currentUpperBound) || (userInputNumber < currentLowerBound)) {
            userIllegalInputCount++;
            roundResult = OUT_OF_BOUNDS;
        } else if (userInputNumber > pickedNumber) {
            currentUpperBound = userInputNumber - 1;            // "-1" to exclude input number from range
            roundResult = GREATER_THAN_PICKED_NUMBER;
        } else if (userInputNumber < pickedNumber) {
            currentLowerBound = userInputNumber + 1;            // "+1" to exclude input number from range
            roundResult = LESS_THAN_PICKED_NUMBER;
        } else {
            roundResult = EQUALS_TO_PICKED_NUMBER;
        }
    }

    private boolean checkStringIsInteger(String string) {
        try {
            int ignored = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    /**
     * Places and returns game model state snapshot into data transfer object to transfer it to another modules
     *
     * @return the state of game model unit.
     */
    public ModelStateDTO getModelState() {
        ModelStateDTO modelState = new ModelStateDTO();
        modelState.setCurrentLowerBound(currentLowerBound);
        modelState.setCurrentUpperBound(currentUpperBound);
        modelState.setRoundResult(roundResult);
        modelState.setUserTries(Collections.unmodifiableList(userInputs));
        modelState.setUserIllegalInputCount(userIllegalInputCount);
        modelState.setUserCorrectInputCount(userCorrectInputCount);
        return modelState;
    }

    /**
     * Sets the model behaivour on non integer user input values
     *
     * @param includeIllegalNonIntegerInputsToHistory If true will include non numeric user input values to history and statistics
     */
    public void setIncludeIllegalNonIntegerInputsToHistory(boolean includeIllegalNonIntegerInputsToHistory) {
        this.includeIllegalNonIntegerInputsToHistory = includeIllegalNonIntegerInputsToHistory;
    }
}
