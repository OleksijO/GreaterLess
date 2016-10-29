package game.greater_less;

import game.greater_less.model.Model;
import game.greater_less.model.ModelStateDTO;
import game.greater_less.model.RoundResult;

import java.util.Scanner;

/**
 * Created by oleksij.onysymchuk@gmail on 29.10.2016.
 */
public class Controller {
    public static final String EXIT = "exit";
    private Model model;
    private View view;

    public void playGame() {
        String userInput;
        Scanner scanner = new Scanner(System.in);

        view.showGreeting(Model.LOWER_BOUND, Model.UPPER_BOUND);
        view.showPromt();
        while (!(userInput = inputStringValueWithScanner(scanner)).toLowerCase().equals(EXIT)) {
            RoundResult roundResult = model.performRoundWithUserInput(userInput);
            ModelStateDTO modelState = model.getModelState();
            if (roundResult == RoundResult.EQUALS_TO) {
                view.showNextRoundInfo(modelState);
                view.showStatistics(modelState);
                return;
            } else {
                view.showNextRoundInfo(modelState);
                view.showPromt();
            }
        }
    }


    // The Utility methods
    private String inputStringValueWithScanner(Scanner scanner) {

        return scanner.next();
    }


    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }
}
