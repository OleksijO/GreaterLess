package game.greater_less.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static game.greater_less.model.RoundResult.*;

/**
 * This class represents Model unit of MVC based architecture program game "Greater/Less".
 * It contains game logic and state.
 * Objective of the game is to guess number, picked by computer in random way, in game range
 * by step by step tries.
 * On every try computer tells user if user's input correct number, greater/less than picked number,
 * out of current game range  or equals to picked number. After every try current game range becomes smaller
 * and closer to picked value.
 * After picked number has been guessed use's tries statistics appear and game is over.
 *
 * @author  oleksij.onysymchuk@gmail
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
    /* Container for storing user try guess inputs */
    private List<String> userInputs;
    /* The result of last round of game on user try input */
    private RoundResult roundResult;

    /* Counter for illegal user inputs - non numbers and out of range. The counter is used for after game statistics  */
    private int userIllegalInputCount;
    /* Counter for numeric user inputs. The counter is used for after game statistics  */
    private int userCorrectInputCount;
     /* this value determines including or not non number inputs to container of user tries and game statistics  */
    private boolean includeIllegalNonNumberInputsToHistory = true;

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
     * @param userInput
     *        The user's input value
     */
    public void performRoundWithUserInput(String userInput) {

        if (!checkStringIsInteger(userInput) && includeIllegalNonNumberInputsToHistory) {
            userIllegalInputCount++;
            userInputs.add(userInput);
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
            currentUpperBound = userInputNumber-1;
            roundResult = GREATER_THAN_PICKED_NUMBER;
        } else if (userInputNumber < pickedNumber) {
            currentLowerBound = userInputNumber+1;
            roundResult = LESS_THAN_PICKED_NUMBER;
        } else {
            roundResult = EQUALS_TO_PICKED_NUMBER;
        }
    }

    private boolean checkStringIsInteger(String string) {
        try {
            int i = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    /**
     * Places and returns game model state into data transfer object to transfer it to another modules
     *
     * @return  the state of game model unit.
     */
    public ModelStateDTO getModelState() {
        ModelStateDTO modelstate = new ModelStateDTO();
        modelstate.setCurrentLowerBound(currentLowerBound);
        modelstate.setCurrentUpperBound(currentUpperBound);
        modelstate.setRoundResult(roundResult);
        modelstate.setUserTries(Collections.unmodifiableList(userInputs));
        modelstate.setUserIllegalInputCount(userIllegalInputCount);
        modelstate.setUserCorrectInputCount(userCorrectInputCount);
        return modelstate;
    }

    /**
     * Sets the model behaivour on non numeric user input values
     *
      * @param includeIllegalNonNumberInputsToHistory
     *          If true will include non numeric user input values to history and statistics
     */
    public void setIncludeIllegalNonNumberInputsToHistory(boolean includeIllegalNonNumberInputsToHistory) {
        this.includeIllegalNonNumberInputsToHistory = includeIllegalNonNumberInputsToHistory;
    }
}
