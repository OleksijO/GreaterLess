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
    private RoundResult roundResult;

    private int userIllegalInputCount;
    private int userCorrectInputCount;
    private boolean includeIllegalInputsToHistory = true;

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

    public void performRoundWithUserInput(String userInput) {
        if (checkStringIsInteger(userInput)) {
            userIllegalInputCount++;
            roundResult = ILLEGAL_INPUT;
            if (includeIllegalInputsToHistory) {
                userInputs.add(userInput);
            }
            return;
        }

        int userInputNumber = Integer.parseInt(userInput);
        userInputs.add(userInput);
        userCorrectInputCount++;
        if ((userInputNumber > currentUpperBound) || (userInputNumber < currentLowerBound)) {
            userIllegalInputCount++;
            roundResult = OUT_OF_BOUNDS;
        } else if (userInputNumber > pickedNumber) {
            currentUpperBound = userInputNumber;
            roundResult = GREATER_THAN_PICKED_NUMBER;
        } else if (userInputNumber < pickedNumber) {
            currentLowerBound = userInputNumber;
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

    public ModelStateDTO getModelState() {
        ModelStateDTO roundInfo = new ModelStateDTO();
        roundInfo.setCurrentLowerBound(currentLowerBound);
        roundInfo.setCurrentUpperBound(currentUpperBound);
        roundInfo.setRoundResult(roundResult);
        roundInfo.setUserTries(Collections.unmodifiableList(userInputs));
        roundInfo.setUserIllegalInputCount(userIllegalInputCount);
        return roundInfo;
    }


    public void setIncludeIllegalInputsToHistory(boolean includeIllegalInputsToHistory) {
        this.includeIllegalInputsToHistory = includeIllegalInputsToHistory;
    }
}
