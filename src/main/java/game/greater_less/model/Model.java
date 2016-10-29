package game.greater_less.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static game.greater_less.model.RoundResult.*;

/**
 * Created by oleksij.onysymchuk@gmail on 29.10.2016.
 */
public class Model {
    public static final int LOWER_BOUND = 0;
    public static final int UPPER_BOUND = 100;

    private int pickedNumber;
    private int currentLowerBound;
    private int currentUpperBound;
    private List<String> userInputs;
    private RoundResult lastRoundResult;

    private int userIllegalInputCount;
    private boolean includeIllegalInputsToHistory=true;

    public Model() {
        init();
    }

    private void init() {
        pickedNumber = (int) (LOWER_BOUND + Math.random() * (UPPER_BOUND - LOWER_BOUND + 1));
        currentLowerBound = LOWER_BOUND;
        currentUpperBound = UPPER_BOUND;
        userInputs = new ArrayList<>();
        userIllegalInputCount = 0;
    }

    public RoundResult performRoundWithUserInput(String userInput) {
        int userInputNumber;

        // check for nonInteger user input and parse if it is integer.
        if (includeIllegalInputsToHistory) {
            userInputs.add(userInput);
        }
        try {
            userInputNumber = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            userIllegalInputCount++;
            lastRoundResult = ILLEGAL_INPUT;
            return ILLEGAL_INPUT;
        }


        if ((userInputNumber > currentUpperBound) || (userInputNumber < currentLowerBound)) {
            lastRoundResult = OUT_OF_BOUNDS;

        } else if (userInputNumber > pickedNumber) {
            currentUpperBound = userInputNumber;
            lastRoundResult = GREATER_THAN;
        } else if (userInputNumber < pickedNumber) {
            currentLowerBound = userInputNumber;
            lastRoundResult = LESS_THAN;
        } else {
            lastRoundResult = EQUALS_TO;
        }
        return lastRoundResult;
    }

    public ModelStateDTO getModelState() {
        ModelStateDTO roundInfo = new ModelStateDTO();
        roundInfo.setCurrentLowerBound(currentLowerBound);
        roundInfo.setCurrentUpperBound(currentUpperBound);
        roundInfo.setLastTryResult(lastRoundResult);
        roundInfo.setUserTries(Collections.unmodifiableList(userInputs));
        roundInfo.setUserIllegalInputCount(userIllegalInputCount);
        return roundInfo;
    }


    public void setIncludeIllegalInputsToHistory(boolean includeIllegalInputsToHistory) {
        this.includeIllegalInputsToHistory = includeIllegalInputsToHistory;
    }
}
