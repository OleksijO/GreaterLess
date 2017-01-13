package training.game.greater.less.controller.command.get;

import training.game.greater.less.Model;
import training.game.greater.less.View;
import training.game.greater.less.controller.Command;
import training.game.greater.less.controller.config.Attributes;
import training.game.greater.less.controller.config.Pages;
import training.game.greater.less.controller.config.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static training.game.greater.less.controller.config.Attributes.*;

/**
 * Created by oleksij.onysymchuk@gmail on 11.01.2017.
 */
public class GetGameCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (hasSessionCorrectAttributes(session)) {
            List<String> history = (List<String>) request.getSession().getAttribute(HISTORY);
            history.add(View.GAME_TITLE);
            history.add(View.OBJECTIVE_IS_GUESS_NUMBER_IN_RANGE);
            request.setAttribute(USUAL_MESSAGE, "The Game Is On!");
            Model model = (Model) request.getSession().getAttribute(MODEL);
            request.setAttribute(Attributes.LOWER_BOUND, model.getLowerBound());
            request.setAttribute(Attributes.UPPER_BOUND, model.getUpperBound());
            return Pages.GAME_PAGE;
        } else {
            response.sendRedirect(Paths.SETUP_PATH);
            return "REDIRECTED";
        }
    }

    private boolean hasSessionCorrectAttributes(HttpSession session) {
        if (session.getAttribute(HISTORY) == null) return false;
        if (session.getAttribute(MODEL) == null) return false;
        return true;

    }
}
