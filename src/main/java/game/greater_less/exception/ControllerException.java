package game.greater_less.exception;

/**
 * Created by oleksij.onysymchuk@gmail on 30.10.2016.
 */
public class ControllerException extends RuntimeException{
    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }
}
