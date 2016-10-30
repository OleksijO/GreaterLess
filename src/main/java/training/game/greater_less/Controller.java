package training.game.greater_less;

import training.game.greater_less.exception.ControllerException;
import training.game.greater_less.model.Model;
import training.game.greater_less.model.ModelStateSnapshotDTO;
import training.game.greater_less.model.RoundResult;

import java.util.Scanner;

import static training.game.greater_less.model.ModelStateSnapshotDTO.UNEXPECTED_EMPTY_MODEL_STATE;

/**
 * This class represents Controller unit of MVC based architecture of program the game "Greater/Less".
 * It contains user input tool and main game cycle.
 *
 * @author oleksij.onysymchuk@gmail
 */
public class Controller {
    public static final String EXIT = "exit";
    private Model model;
    private View view;

    /**
     * Initiates game's controller unit. Properties model and view must be set addionally !
     */
    public Controller() {
    }

    /**
     * Initiates game's controller unit.
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     *  runs main game cycle
     */
    public void playGame() {
        try {
            String userInput;
            Scanner scanner = new Scanner(System.in);

            view.showGreeting(Model.LOWER_BOUND, Model.UPPER_BOUND);
            view.showPromt();
            while (!(userInput = inputStringValueWithScanner(scanner)).toLowerCase().equals(EXIT)) {
                model.performRoundWithUserInput(userInput);
                ModelStateSnapshotDTO modelState = model.getModelState();
                if ((modelState == null) || (!modelState.isValid())) {
                    String errorMessage = UNEXPECTED_EMPTY_MODEL_STATE + (modelState == null ? null : modelState.toString());
                    view.showErrorMessage(errorMessage);
                    throw new ControllerException(errorMessage);
                }
                view.showRoundInfo(modelState);
                if (modelState.getRoundResult() == RoundResult.EQUALS_TO_PICKED_NUMBER) {
                    view.showStatistics(modelState);
                    return;
                } else {
                    view.showPromt();
                }
            }
        } catch (Exception e){
            view.showErrorMessage(e.getClass().getSimpleName()+ "\n" +e.getMessage());
        }
    }


    private String inputStringValueWithScanner(Scanner scanner) {
        return scanner.next();
    }


    /**
     * sets  the value of model property.
     * This propertie refers to model unit of the game proram
     * @param model
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * sets  the value of view property.
     * This propertie refers to view unit of the game proram
     * @param view
     */
    public void setView(View view) {
        this.view = view;
    }
}
