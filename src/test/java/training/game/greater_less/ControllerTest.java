package training.game.greater_less;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Scanner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This test class is using for test controller unit
 *
 * @author oleksij.onysymchuk@gmail
 */
public class ControllerTest {


    @Test
    public void testUserInputs() throws NoSuchFieldException, IllegalAccessException {
        Model model = new Model();
        View view = new View();
        String input[] = {"-50","140","ss", "40", "40", "60", "150", "50"};
        Integer[] inputValues = {40, 60, 50};
        // replacing user input to strings from array
        Controller controller = new Controller(model, view) {
            private int counter = 0;

            @Override
            protected void initModel(int lowerBound, int upperBound) {
                model.setGameRange(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
                try {
                    Field field = Model.class.getDeclaredField("pickedValue");
                    field.setAccessible(true);
                    field.set(model, 50);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public String inputStringValueWithScanner(Scanner scanner) {
                System.out.println(input[counter]);
                return input[counter++];
            }
        };
        controller.playGame();

        Integer[] testArray = new Integer[model.getUserInputHistory().size()];
        model.getUserInputHistory().toArray(testArray);
        assertArrayEquals(inputValues, testArray);
    }

    @Test
    public void testInitModel() throws NoSuchFieldException, IllegalAccessException {
        Model model = new Model();
        View view = new View();
        model.setGameRange(-5, 5);
        Field field = Model.class.getDeclaredField("pickedValue");
        field.setAccessible(true);
        field.set(model, 50);

        Controller controller = new Controller(model, view) {

            @Override
            public void initModel(int lowerBound, int upperBound) {
                super.initModel(lowerBound, upperBound);
            }

        };
        controller.initModel(90,100);
        assertEquals(90, model.getLowerBound());
        assertEquals(100, model.getUpperBound());
        assertTrue((90<model.getPickedValue())&&(model.getPickedValue()<100));

    }
}
