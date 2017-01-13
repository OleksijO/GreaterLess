package training.game.greater.less.controller.command.post;

import training.game.greater.less.Model;
import training.game.greater.less.controller.Command;
import training.game.greater.less.controller.command.GameCommand;
import training.game.greater.less.controller.command.helper.ParameterExtractor;
import training.game.greater.less.controller.config.Attributes;
import training.game.greater.less.controller.config.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static java.lang.String.format;
import static training.game.greater.less.View.*;
import static training.game.greater.less.controller.config.Attributes.ERROR_MESSAGE;
import static training.game.greater.less.controller.config.Attributes.USUAL_MESSAGE;
import static training.game.greater.less.controller.config.Parameters.USER_INPUT_NUMBER;

/**
 * Created by oleksij.onysymchuk@gmail on 11.01.2017.
 */
public class PostGameCommand implements Command, ParameterExtractor, GameCommand {

    public static final String ILLEGAL_GAME_STATE = "Illegal game state!";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Model model = restoreModel(request);
        List<String> history = restoreHistory(request);
        if ((model == null) || (history == null)) {
            putErrorMessageToView(request, ILLEGAL_GAME_STATE);
            return Pages.GAME_PAGE;
        }
        int userValue;
        try {
            userValue =
                    getIntFromRequest(request, USER_INPUT_NUMBER, ILLEGAL_INPUT);
        } catch (Exception e) {
            putErrorMessageToView(request, e.getMessage());
            return Pages.GAME_PAGE;
        }
        history.add("Input value: " + userValue);
        if (!model.isUserInputValueIsInRange(userValue)) {
            putErrorMessageToView(request, RESULT_OUT_OF_BOUNDS);
            putGameRangeToRequest(request);
            return Pages.GAME_PAGE;
        }

        putGameRangeToRequest(request);
        processUserInputValue(request, userValue);
        return Pages.GAME_PAGE;


    }

    private void putErrorMessageToView(HttpServletRequest request, String message) {
        request.setAttribute(ERROR_MESSAGE, message);
        restoreHistory(request).add(message);
    }

    private void processUserInputValue(HttpServletRequest request, int userValue) {
        int result = restoreModel(request).checkUserValue(userValue);
        if (result == Model.GREATER) {
            putErrorMessageToView(request, RESULT_GREATER_THAN);
        } else if (result == Model.EQUALS) {
            putGameOverTextBlockOnView(request, userValue);
        } else {
            putErrorMessageToView(request, RESULT_LESS_THAN);
        }
    }

    private void putGameOverTextBlockOnView(HttpServletRequest request, int userValue) {
        Model model = restoreModel(request);
        List<String> history = restoreHistory(request);
        request.setAttribute(Attributes.GAME_OVER, true);
        history.add(format(RESULT_EQUALS_TO, userValue));
        history.add(format(YOU_INPUT_VALUE_HISTORY_IS, model.getUserInputHistory().toString()));
        history.add(format(TOTAL_TRIES_COUNT, model.getUserInputHistory().size()));
        request.setAttribute(USUAL_MESSAGE, format(RESULT_EQUALS_TO, userValue));
    }

}
