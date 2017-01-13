package training.game.greater.less.controller.command;

import training.game.greater.less.Model;
import training.game.greater.less.controller.config.Attributes;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static training.game.greater.less.controller.config.Attributes.HISTORY;
import static training.game.greater.less.controller.config.Attributes.MODEL;

/**
 * Created by oleksij.onysymchuk@gmail on 13.01.2017.
 */
public interface GameCommand {

    default void putGameRangeToRequest(HttpServletRequest request) {
        Model model = restoreModel(request);
        request.setAttribute(Attributes.LOWER_BOUND, model.getLowerBound());
        request.setAttribute(Attributes.UPPER_BOUND, model.getUpperBound());
    }

    default List<String> restoreHistory(HttpServletRequest request) {
        return (List<String>) request.getSession().getAttribute(HISTORY);
    }

    default Model restoreModel(HttpServletRequest request) {
        return (Model) request.getSession().getAttribute(MODEL);
    }
}
