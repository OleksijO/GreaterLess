package training.game.greater.less.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by oleksij.onysymchuk@gmail on 11.01.2017.
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
