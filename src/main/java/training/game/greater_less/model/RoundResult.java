package training.game.greater_less.model;

/**
 * this enum represents all possible results of game round calculation after any value was input by user
 * and transferred to model unit to perform next round of the game.
 *
 * @author  oleksij.onysymchuk@gmail
 */
public enum RoundResult {
    /**
     * The value indicates, that last user's input was greater than picked value
     */
    GREATER_THAN_PICKED_NUMBER,

    /**
     * The value indicates, that last user's input was less than picked value
     */
    LESS_THAN_PICKED_NUMBER,

    /**
     * The value indicates, that last user's input was equals to picked value
     */
    EQUALS_TO_PICKED_NUMBER,

    /**
     * The value indicates, that last user's input was out of current game range bounds
     */
    OUT_OF_BOUNDS,

    /**
     * The value indicates, that last user's input was non integer value
     */
    ILLEGAL_INPUT
}
