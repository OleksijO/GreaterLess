package game.greater_less.model;

import java.util.List;

import static game.greater_less.model.Model.UPPER_BOUND;

/**
 * This class describes data transfer object, which represents game state after each round (step).
 * It is used for transfer data between MVC components.
 *
 * @author oleksij.onysymchuk@gmail
 */
public class ModelStateSnapshotDTO {
    /**
     * The value is the error message when the model state is invalid and is used mainly as exception message
     */
    public static final String UNEXPECTED_EMPTY_MODEL_STATE = "Unexpected empty/null/nonValid modelState: ";

    /* The values below represents corresponding values of model unit */
    private int currentLowerBound;
    private int currentUpperBound;
    private List<String> userInputHistory;
    private RoundResult roundResult;
    private int userIllegalInputCount;
    private int userCorrectInputCount;

    /**
     * @return returns true if data in this representation of model state are valid, if other - false
     */
    public boolean isValid() {
        if ((currentLowerBound < Model.LOWER_BOUND) ||
                (currentUpperBound > UPPER_BOUND) ||
                (currentLowerBound > currentUpperBound) ||
                (userInputHistory == null) ||
                (roundResult == null) ||
                (userCorrectInputCount > userInputHistory.size())) {
            return false;
        }

        return true;
    }

    /**
     * @return returns text representation of model state DTO
     */
    @Override
    public String toString() {
        return "ModelStateSnapshotDTO{" +
                "currentLowerBound=" + currentLowerBound +
                ", currentUpperBound=" + currentUpperBound +
                ", userInputHistory=" + userInputHistory +
                " [last='" + userInputHistory.get(userInputHistory.size() - 1) +
                "',size=" + userInputHistory.size() + "]"+
                ", roundResult=" + roundResult.toString() +
                ", userIllegalInputCount=" + userIllegalInputCount +
                ", userCorrectInputCount=" + userCorrectInputCount +
                '}';
    }


    /**
     * @return returns property of current (in-game) lower bound of range
     */
    public int getCurrentLowerBound() {
        return currentLowerBound;
    }

    /**
     * @return returns property of current (in-game) upper bound of range
     */
    public int getCurrentUpperBound() {
        return currentUpperBound;
    }

    /**
     * @return returns history of user's input values
     */
    public List<String> getUserInputHistory() {
        return userInputHistory;
    }

    /**
     * @return returns round result after last user's input value
     */
    public RoundResult getRoundResult() {
        return roundResult;
    }

    /**
     * @return returns counter's value of user's illegal input values non integer or out of bounds (depends
     * on {@code Model.includeIllegalNonIntegerInputsToHistory} property).
     * The counter is used for after game statistics
     */
    public int getUserIllegalInputCount() {
        return userIllegalInputCount;
    }

    /**
     * @return returns counter's value of user's correct (integer) input values.
     * The counter is used for after game statistics
     */
    public int getUserCorrectInputCount() {
        return userCorrectInputCount;
    }

    public void setCurrentLowerBound(int currentLowerBound) {
        this.currentLowerBound = currentLowerBound;
    }

    public void setCurrentUpperBound(int currentUpperBound) {
        this.currentUpperBound = currentUpperBound;
    }

    public void setUserInputHistory(List<String> userInputHistory) {
        this.userInputHistory = userInputHistory;
    }

    public void setRoundResult(RoundResult roundResult) {
        this.roundResult = roundResult;
    }

    public void setUserIllegalInputCount(int userIllegalInputCount) {
        this.userIllegalInputCount = userIllegalInputCount;
    }

    public void setUserCorrectInputCount(int userCorrectInputCount) {
        this.userCorrectInputCount = userCorrectInputCount;
    }
}
