package game.greater_less.model;

import java.util.List;

/**
 * Created by oleksij.onysymchuk@gmail on 30.10.2016.
 */
public class ModelStateDTO {
    private int currentLowerBound;
    private int currentUpperBound;
    private List<String> userTries;
    private RoundResult roundResult;
    private int userIllegalInputCount;
    private int userCorrectInputCount;

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
