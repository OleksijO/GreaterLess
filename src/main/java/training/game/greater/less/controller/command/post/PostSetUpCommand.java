package training.game.greater.less.controller.command.post;

import training.game.greater.less.Model;
import training.game.greater.less.controller.Command;
import training.game.greater.less.controller.command.helper.ParameterExtractor;
import training.game.greater.less.controller.config.Attributes;
import training.game.greater.less.controller.config.Pages;
import training.game.greater.less.controller.config.Parameters;
import training.game.greater.less.controller.config.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static training.game.greater.less.controller.config.Parameters.SETUP_RANGE_LOWER_BOUND;

/**
 * Created by oleksij.onysymchuk@gmail on 11.01.2017.
 */
public class PostSetUpCommand implements Command, ParameterExtractor {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int lowerBound =
                    getIntFromRequest(request, SETUP_RANGE_LOWER_BOUND, "Lower bound should be INT");
            int upperBound =
                    getIntFromRequest(request, Parameters.SETUP_RANGE_UPPER_BOUND, "Lower bound should be INT");
            Model model = initModel(lowerBound, upperBound);
            storeObjectInSession(model, Attributes.MODEL, request.getSession());
            storeObjectInSession(new ArrayList<String>(), Attributes.HISTORY, request.getSession());
            response.sendRedirect(Paths.GAME_PATH);
            return "REDIRECTED";
        } catch (Exception e) {
            request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
            e.printStackTrace();
            return Pages.SETUP_PAGE;
        }

    }

    private void storeObjectInSession(Object object, String attributeName, HttpSession session) {
        session.setAttribute(attributeName, object);
    }

    private Model initModel(int lowerBound, int upperBound) {
        Model model = new Model();
        model.setGameRange(lowerBound, upperBound);
        model.pickNumber();
        return model;
    }


}
