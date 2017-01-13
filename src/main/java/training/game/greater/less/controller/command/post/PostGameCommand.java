package training.game.greater.less.controller.command.post;

import training.game.greater.less.Model;
import training.game.greater.less.controller.Command;
import training.game.greater.less.controller.command.GameCommand;
import training.game.greater.less.controller.command.helper.ParameterExtractor;
import training.game.greater.less.controller.config.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.String.format;
import static training.game.greater.less.controller.config.Attributes.*;
import static training.game.greater.less.controller.config.Parameters.USER_INPUT_NUMBER;
import static training.game.greater.less.controller.config.Paths.REDIRECTED;
import static training.game.greater.less.controller.config.Paths.SETUP_PATH;
import static training.game.greater.less.controller.config.ViewMessages.*;

/**
 * Created by oleksij.onysymchuk@gmail on 11.01.2017.
 */
public class PostGameCommand implements Command, ParameterExtractor, GameCommand {

    public static final String ILLEGAL_GAME_STATE = "Illegal game state!";
    public static final String INPUT_VALUE = "Input value: ";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Model model = restoreModel(request);
        List<String> history = restoreHistory(request);
        if ((model == null) || (history == null)) {
            response.sendRedirect(SETUP_PATH);
            return REDIRECTED;
        }

        int userValue;
        try {
            userValue =
                    getIntFromRequest(request, USER_INPUT_NUMBER, ILLEGAL_INPUT);
        } catch (Exception e) {
            putErrorMessageToView(request, e.getMessage());
            return Pages.GAME_PAGE;
        }

        putUserInpuValueToView(request, userValue);

        if (!model.isUserInputValueIsInRange(userValue)) {
            putErrorMessageToView(request, RESULT_OUT_OF_BOUNDS);
            putGameRangeToRequest(request);
            return Pages.GAME_PAGE;
        }

        processUserInputValue(request, userValue);
        putGameRangeToRequest(request);

        return Pages.GAME_PAGE;
    }

    private void putInternalErrorMessageToView(HttpServletRequest request, String message) {
        request.setAttribute(ERROR_MESSAGE, message);
    }

    private void putUserInpuValueToView(HttpServletRequest request, int userValue) {
       restoreHistory(request).add(INPUT_VALUE + userValue);
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
        request.setAttribute(GAME_IS_OVER, true);
        history.add(format(RESULT_EQUALS_TO, userValue));
        history.add(format(YOU_INPUT_VALUE_HISTORY_IS, model.getUserInputHistory().toString()));
        history.add(format(TOTAL_TRIES_COUNT, model.getUserInputHistory().size()));
        request.setAttribute(USUAL_MESSAGE, format(RESULT_EQUALS_TO, userValue));
    }

}
