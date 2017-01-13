package training.game.greater.less.controller.command.helper;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by oleksij.onysymchuk@gmail on 13.01.2017.
 */
public interface ParameterExtractor {

    default int getIntFromRequest(HttpServletRequest request, String param, String errorMessage) {
        try {
            return Integer.parseInt(request.getParameter(param));
        } catch (NumberFormatException e) {
            throw new RuntimeException(errorMessage, e);
        }
    }
}
