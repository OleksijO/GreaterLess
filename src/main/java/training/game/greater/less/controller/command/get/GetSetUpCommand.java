package training.game.greater.less.controller.command.get;

import training.game.greater.less.controller.Command;
import training.game.greater.less.controller.config.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by oleksij.onysymchuk@gmail on 11.01.2017.
 */
public class GetSetUpCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Pages.SETUP_PAGE;
    }
}
