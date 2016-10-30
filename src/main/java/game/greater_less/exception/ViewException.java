package game.greater_less.exception;

/**
 * This exception is used to describe unusual situation in View unit of MVC program architecture
 * @author oleksij.onysymchuk@gmail
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
