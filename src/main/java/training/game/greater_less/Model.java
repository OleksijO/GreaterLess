package training.game.greater_less;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents Model unit of MVC based architecture of program the game "Greater/Less".
 * It contains game logic and state.
 * Objective of the game is to guess integer, picked (generated) by computer in random way, in game range
 * by step by step attempts.
 * On every try computer tells user if user's input correct number, greater/less than picked number,
 * out of current game range  or equals to picked number. After every try current game range becomes smaller
 * and closer to picked value.
 * After picked number has been guessed use's tries statistics appears and game is over.
 *
 * @author oleksij.onysymchuk@gmail
 */
public class Model {
    /* Error messages for case of calling model methods in wrong order */
    public static final String CHECK_OUT_OF_RANGE_FIRST = "You need to check 'out of range' before checking greater/less!";
    public static final String SET_BOUNDS_BEFORE_PICKING_VALUE = "You need to set up bounds before picking value!";
    public static final String SET_BOUNDS_BEFORE_PLAYING_GAME = "You need to set up bounds before playing game!";
    public static final String WRONG_BOUNDS = "Lower bound must be strictly less than upper one for a min difference of 2!";
    /* Results constants of check for greater/less */
    public static final int GREATER = 1;
    public static final int EQUALS = 0;
    public static final int LESS = -1;
    /**
     * The picked by computer randome value in initial game range, which user has to guess
     */
    private int pickedValue;
    /**
     * The value of current (in-game) lower bound (not included) of initial game range
     */
    private int lowerBound;
    /**
     * The value of current (in-game) lower bound (not included) of initial game range
     */
    private int upperBound;
    /**
     * Container for storing user attepts, i.e. user input values history
     */
    private List<Integer> userInputHistory = new ArrayList<>();

    /**
     * Generates random value in specified range
     *
     * @throws RuntimeException in case of bounds are not set up yet.
     */
    public void pickNumber() {
        if (isCorrectBounds()) {
            do {
                pickedValue = (int) (lowerBound + 1 + getRandomValueFrom0To1() * (upperBound - lowerBound - 1));
            } while ((pickedValue <= lowerBound) && (pickedValue >= upperBound));
        } else {
            throw new RuntimeException(SET_BOUNDS_BEFORE_PICKING_VALUE);
        }
    }

    protected double getRandomValueFrom0To1() {
        return Math.random();
    }

    private boolean isCorrectBounds() {
        return upperBound > lowerBound + 1;
    }

    /**
     * Checkes if user input value is within current game range.
     * NOTE. This method must be called before checking greater/less or equals to picked value.
     *
     * @param userInputValue next user attempt to guess value
     * @throws RuntimeException in case of bounds are not set up yet.
     */
    public boolean isUserInputValueIsInRange(int userInputValue) {
        if (!isCorrectBounds()) {
            throw new RuntimeException(SET_BOUNDS_BEFORE_PLAYING_GAME);
        }
        return ((userInputValue > lowerBound) && (userInputValue < upperBound));
    }

    /**
     * Compares user input value to picked value and returns result similar to method compareTo().
     * NOTE. You must check if user input value is in current game range first or RuntimeExceprion will be thrown!
     *
     * @param userValue next user attempt to guess value
     * @return returns:
     * {@code          -1 if the parameter is less than picked number;      }
     * {@code           0 if the parameter is equals to picked number;      }
     * {@code          +1 if the parameter is greater than picked number;   }
     * @throws RuntimeException in case if the parameter is out of game range.
     */
    public int checkUserValue(int userValue) {
        if (!isCorrectBounds()) {
            throw new RuntimeException(WRONG_BOUNDS);
        }
        if ((userValue >= upperBound) || (userValue <= lowerBound)) {
            throw new RuntimeException(CHECK_OUT_OF_RANGE_FIRST);
        }

        userInputHistory.add(userValue);
        if (userValue == pickedValue) {
            return EQUALS;
        } else if (userValue > pickedValue) {
            upperBound = userValue;
            return GREATER;
        } else {
            lowerBound = userValue;
            return LESS;
        }
    }

    /**
     * @param lowerBound the value of lower bound of initial game range
     * @param upperBound the value of upper bound of initial game range
     * @throws RuntimeException in case if the lower bound is not less than upper one min for 2.
     */
    public void setGameRange(int lowerBound, int upperBound) {
        if (upperBound > lowerBound + 1) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            return;
        }
        throw new RuntimeException(WRONG_BOUNDS);

    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public int getPickedValue() {
        return pickedValue;
    }

    public List<Integer> getUserInputHistory() {
        return userInputHistory;
    }
}
