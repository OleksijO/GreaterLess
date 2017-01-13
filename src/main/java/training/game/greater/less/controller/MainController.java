package training.game.greater.less.controller;

import training.game.greater.less.controller.command.get.GetGameCommand;
import training.game.greater.less.controller.command.get.GetHomeCommand;
import training.game.greater.less.controller.command.get.GetSetUpCommand;
import training.game.greater.less.controller.command.post.PostGameCommand;
import training.game.greater.less.controller.command.post.PostSetUpCommand;
import training.game.greater.less.controller.config.Attributes;
import training.game.greater.less.controller.config.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MainController")
public class MainController extends HttpServlet {
    private final Map<String, Command> getCommands = new HashMap<String, Command>() {{
        put("/setup", new GetSetUpCommand());
        put("/game", new GetGameCommand());
        put("/", new GetHomeCommand());
    }};
    private final Map<String, Command> postCommands = new HashMap<String, Command>() {{
        put("/setup", new PostSetUpCommand());
        put("/game", new PostGameCommand());
    }};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processCommand(postCommands.get(request.getRequestURI()), request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processCommand(getCommands.get(request.getRequestURI()), request, response);
    }

    private void processCommand(Command command, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (command == null) {
            request.setAttribute(Attributes.ERROR_MESSAGE, "Internal error.");
            response.sendRedirect(Paths.HOME_PATH);
            return;
        }
        String view = command.execute(request, response);
        if (!view.equals("REDIRECTED")) {
            request.getRequestDispatcher(view).forward(request, response);
        }
    }
}
