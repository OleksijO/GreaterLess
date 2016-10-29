package game.greater_less.model;

import java.util.List;

/**
 * Created by oleksij.onysymchuk@gmail on 30.10.2016.
 */
public class ModelStateDTO {
    private int currentLowerBound;
    private int currentUpperBound;
    private List<String> userTries;
    private RoundResult lastTryResult;
    private int userIllegalInputCount;

    public int getCurrentLowerBound() {
        return currentLowerBound;
    }

    public int getCurrentUpperBound() {
        return currentUpperBound;
    }

    public List<String> getUserTries() {
        return userTries;
    }

    public RoundResult getLastTryResult() {
        return lastTryResult;
    }

    public int getUserIllegalInputCount() {
        return userIllegalInputCount;
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

    public void setLastTryResult(RoundResult lastTryResult) {
        this.lastTryResult = lastTryResult;
    }

    public void setUserIllegalInputCount(int userIllegalInputCount) {
        this.userIllegalInputCount = userIllegalInputCount;
    }
}
