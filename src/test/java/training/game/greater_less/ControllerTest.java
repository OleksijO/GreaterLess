package training.game.greater_less;

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
 *
 * @author oleksij.onysymchuk@gmail
 */
public class ControllerTest {


    @Test
    public void testUserInputExit() {
        StubModel model = new StubModel();
        model.setIncludeIllegalNonIntegerInputsToHistory(true);
        StubView view = new StubView();
        final String userInput = "Exit";
        Controller controller = new Controller(model, view) {
            @Override
            protected String inputStringValueWithScanner(Scanner scanner) {
                return userInput;
            }
        };
        controller.playGame();
        assertEquals("", model.getUserInput());
        assertEquals(1, view.getGreetingCounter());
        assertEquals(0, view.getRoundInfoCounter());
        assertEquals(1, view.getShowPromtCounter());
        assertEquals(0, view.getShowStatCounter());
        assertEquals(0, view.getErrorsCounter());

        StubView view1 = new StubView();
        final String userInputNonExit = "No_exit";
        controller = new Controller(model, view1) {
            @Override
            protected String inputStringValueWithScanner(Scanner scanner) {
                return userInputNonExit;
            }
        };
        controller.playGame();
        assertEquals(userInputNonExit, model.getUserInput());
        assertEquals(1, view1.getGreetingCounter());
        assertEquals(0, view1.getRoundInfoCounter());
        assertEquals(1, view1.getShowPromtCounter());
        assertEquals(0, view1.getShowStatCounter());
        assertEquals(1, view1.getErrorsCounter());

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
        assertEquals(1, view.getGreetingCounter());
        assertEquals(5, view.getRoundInfoCounter());
        assertEquals(5, view.getShowPromtCounter());
        assertEquals(1, view.getShowStatCounter());
        assertEquals(0, view.getErrorsCounter());
    }
}
