package training.game.greater_less;

import org.junit.Assert;
import org.junit.Test;
import training.game.greater_less.model.Model;
import training.game.greater_less.model.ModelStateSnapshotDTO;
import training.game.greater_less.model.RoundResult;
import training.game.greater_less.stubs.StubModel;
import training.game.greater_less.stubs.StubView;

import java.lang.reflect.Field;
import java.util.Scanner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * This test class is using for test controller unit
 * @author oleksij.onysymchuk@gmail
 */
public class ControllerTest {


    @Test
    public void testUserInputExit() {
        StubModel model = new StubModel();
        final String userInput = "Exit";
        Controller controller = new Controller(model, new View()) {
            @Override
            protected String inputStringValueWithScanner(Scanner scanner) {
                return userInput;
            }
        };
        controller.playGame();
        Assert.assertEquals("", model.getUserInput());
        final String userInputNonExit = "No_exit";
        controller = new Controller(model, new View()) {
            @Override
            protected String inputStringValueWithScanner(Scanner scanner) {
                return userInputNonExit;
            }
        };
        controller.playGame();
        Assert.assertEquals(userInputNonExit, model.getUserInput());
    }

    @Test
    public void testUserInputs() throws NoSuchFieldException, IllegalAccessException {
        Model model = new Model();
        model.setIncludeIllegalNonIntegerInputsToHistory(true);
        Field field = Model.class.getDeclaredField("pickedNumber");
        field.setAccessible(true);
        field.set(model, 50);
        StubView view = new StubView();
        String input[] = {"ss", "40", "60", "150", "50"};
        // replacing user input to strings from array
        Controller controller = new Controller(model, view) {
            private int counter = 0;

            @Override
            protected String inputStringValueWithScanner(Scanner scanner) {
                return input[counter++];
            }
        };
        controller.playGame();
        ModelStateSnapshotDTO modelState = model.getModelState();
        String[] testArray = new String[modelState.getUserInputHistory().size()];
        modelState.getUserInputHistory().toArray(testArray);
        assertArrayEquals(input, testArray);
        assertEquals(RoundResult.EQUALS_TO_PICKED_NUMBER, modelState.getRoundResult());
    }
}
