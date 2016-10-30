package training.game.greater_less.stubs;

import training.game.greater_less.model.Model;

/**
 * Created by oleksij.onysymchuk@gmail on 30.10.2016.
 */
public class StubModel extends Model {
    String userInput = "";


    @Override
    public void performRoundWithUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getUserInput() {
       return userInput;
    }
}
