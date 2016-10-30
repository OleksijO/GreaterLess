package game.greater_less.exception;

/**
 * This exception is used to describe unusual situation in Controller unit of MVC program architecture
 * @author oleksij.onysymchuk@gmail
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
