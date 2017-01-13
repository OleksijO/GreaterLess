package training.game.greater.less.controller.command.post;

import training.game.greater.less.Model;
import training.game.greater.less.View;
import training.game.greater.less.controller.Command;
import training.game.greater.less.controller.command.helper.ParameterExtractor;
import training.game.greater.less.controller.config.Attributes;
import training.game.greater.less.controller.config.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static java.lang.String.format;
import static training.game.greater.less.View.RESULT_EQUALS_TO;
import static training.game.greater.less.View.TOTAL_TRIES_COUNT;
import static training.game.greater.less.controller.config.Attributes.*;
import static training.game.greater.less.controller.config.Parameters.USER_INPUT_NUMBER;

/**
 * Created by oleksij.onysymchuk@gmail on 11.01.2017.
 */
public class PostGameCommand implements Command {
    private ParameterExtractor parameterExtractor = new ParameterExtractor();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Model model = (Model) request.getSession().getAttribute(MODEL);
        List<String> history = (List<String>) request.getSession().getAttribute(HISTORY);
        if ((model == null) || (history == null)) {
            request.setAttribute(ERROR_MESSAGE, "Illegal game state!");
            return Pages.GAME_PAGE;
        }
        int userValue;
        try {
            userValue =
                    parameterExtractor.getIntFromRequest(request, USER_INPUT_NUMBER, "Input value should be int");
        } catch (Exception e) {
            request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
            e.printStackTrace();
            return Pages.GAME_PAGE;
        }
        history.add("Input value: " + userValue);
        if (!model.isUserInputValueIsInRange(userValue)) {
            request.setAttribute(ERROR_MESSAGE, View.RESULT_OUT_OF_BOUNDS);
            history.add(View.RESULT_OUT_OF_BOUNDS);
            request.setAttribute(Attributes.LOWER_BOUND, model.getLowerBound());
            request.setAttribute(Attributes.UPPER_BOUND, model.getUpperBound());
            return Pages.GAME_PAGE;
        }
        int result = model.checkUserValue(userValue);
        request.setAttribute(Attributes.LOWER_BOUND, model.getLowerBound());
        request.setAttribute(Attributes.UPPER_BOUND, model.getUpperBound());
        if (result == Model.GREATER) {
            request.setAttribute(ERROR_MESSAGE, View.RESULT_GREATER_THAN);
            history.add(View.RESULT_GREATER_THAN);
        } else if (result == Model.EQUALS) {
            request.setAttribute(Attributes.GAME_OVER, true);
            history.add(format(RESULT_EQUALS_TO, userValue));
            history.add("Your input values are " + model.getUserInputHistory().toString());
            history.add(format(TOTAL_TRIES_COUNT, model.getUserInputHistory().size()));
            request.setAttribute(USUAL_MESSAGE, format(RESULT_EQUALS_TO, userValue));

        } else {
            request.setAttribute(ERROR_MESSAGE, View.RESULT_LESS_THAN);
            history.add(View.RESULT_LESS_THAN);
        }
        return Pages.GAME_PAGE;


    }
}
