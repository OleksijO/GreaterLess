package game.greater_less.exception;

/**
 * Created by oleksij.onysymchuk@gmail on 30.10.2016.
 */
public class ViewException extends RuntimeException{
    public ViewException(String message) {
        super(message);
    }

    public ViewException(String message, Throwable cause) {
        super(message, cause);
    }

    public ViewException(Throwable cause) {
        super(cause);
    }
}
