package game.greater_less.model;

import java.util.List;

import static game.greater_less.model.Model.UPPER_BOUND;

/**
 * Created by oleksij.onysymchuk@gmail on 30.10.2016.
 */
public class ModelStateDTO {
    public static final String UNEXPECTED_EMPTY_MODEL_STATE = "Unexpected empty/null/nonValid modelState: ";

    private int currentLowerBound;
    private int currentUpperBound;
    private List<String> userTries;
    private RoundResult roundResult;
    private int userIllegalInputCount;
    private int userCorrectInputCount;

    public boolean isValid() {
        if ((currentLowerBound < Model.LOWER_BOUND) ||
                (currentUpperBound > UPPER_BOUND) ||
                (currentLowerBound > currentUpperBound) ||
                (userTries == null) ||
                (roundResult == null) ||
                (userCorrectInputCount > userTries.size())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "ModelStateDTO{" +
                "currentLowerBound=" + currentLowerBound +
                ", currentUpperBound=" + currentUpperBound +
                ", userTries=" + userTries +
                ", roundResult=" + roundResult +
                ", userIllegalInputCount=" + userIllegalInputCount +
                ", userCorrectInputCount=" + userCorrectInputCount +
                '}';
    }

    public int getCurrentLowerBound() {
        return currentLowerBound;
    }

    public int getCurrentUpperBound() {
        return currentUpperBound;
    }

    public List<String> getUserTries() {
        return userTries;
    }

    public RoundResult getRoundResult() {
        return roundResult;
    }

    public int getUserIllegalInputCount() {
        return userIllegalInputCount;
    }

    public int getUserCorrectInputCount() {
        return userCorrectInputCount;
    }

    public void setCurrentLowerBound(int currentLowerBound) {
        this.currentLowerBound = currentLowerBound;
    }

    public void setCurrentUpperBound(int currentUpperBound) {
        this.currentUpperBound = currentUpperBound;
    }

    public void setUserTries(List<String> userTries) {
        this.userTries = userTries;
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
