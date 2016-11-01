package training.game.greater_less;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * This test class is using for test model's game logic
 *
 * @author oleksij.onysymchuk@gmail
 */
public class ModelTest {

    /**
     * test initial model state
     */
    @Test
    public void testSetGameRange() {
        Model model = new Model();
        int lowerBound = 1;
        int upperBound = 3;
        model.setGameRange(lowerBound, upperBound);
        assertEquals("Lower bound of game range must be " + lowerBound + " after init", lowerBound, model.getLowerBound());
        assertEquals("Upper bound of game range must be " + upperBound + " after init", upperBound, model.getUpperBound());
    }

    @Test(expected = RuntimeException.class)
    public void testSetWrongGameRange() {
        Model model = new Model();
        int lowerBound = 1;
        int upperBound = 2;
        model.setGameRange(lowerBound, upperBound);
    }

    @Test
    public void testIsUserInputValueIsInRange() {
        Model model = new Model();
        int lowerBound = 1;
        int upperBound = 3;
        model.setGameRange(lowerBound, upperBound);
        int userInput = 2;
        assertTrue(model.isUserInputValueIsInRange(userInput));
        userInput = lowerBound;
        assertFalse(model.isUserInputValueIsInRange(userInput));
        userInput = lowerBound - 1;
        assertFalse(model.isUserInputValueIsInRange(userInput));
        userInput = upperBound;
        assertFalse(model.isUserInputValueIsInRange(userInput));
        userInput = upperBound + 1;
        assertFalse(model.isUserInputValueIsInRange(userInput));
    }

    @Test
    public void testMethodsCallOrder() {
        try {
            Model model = new Model();
            int lowerBound = 1;
            int upperBound = 3;
            model.setGameRange(lowerBound, upperBound);
            model.pickNumber();
            model.isUserInputValueIsInRange(2);
            model.checkUserValue(2);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        assertTrue(true);
    }

    @Test(expected = RuntimeException.class)
    public void testCheckUserValueBeforeCheckRange() {
        Model model = new Model();
        int lowerBound = 1;
        int upperBound = 3;
        model.setGameRange(lowerBound, upperBound);
        model.pickNumber();
        model.checkUserValue(lowerBound);
    }

    @Test(expected = RuntimeException.class)
    public void testPickValueBeforSetUpBounds() {
        Model model = new Model();
        model.pickNumber();
    }

    @Test
    public void testCheckRangeBeforeSetUpBounds() {
        Model model = new Model();
        int lowerBound = 1;
        int upperBound = 3;
    }


    @Test
    public void testCheckUserValue() throws NoSuchFieldException, IllegalAccessException {
        Model model = new Model();
        int lowerBound = 0;
        int upperBound = 100;
        model.setGameRange(lowerBound, upperBound);
        Field field = Model.class.getDeclaredField("pickedValue");
        field.setAccessible(true);
        field.set(model, 50);
        assertEquals(Model.GREATER, model.checkUserValue(51));
        assertEquals(Model.LESS, model.checkUserValue(49));
        assertEquals(Model.EQUALS, model.checkUserValue(50));
    }

    @Test
    public void testPickValue() {
        int lowerBound = 0;
        int upperBound = 101;
        Model model = new Model() {
            @Override
            protected double getRandomValueFrom0To1() {
                return 0;
            }
        };
        model.setGameRange(lowerBound, upperBound);
        model.pickNumber();
        assertEquals(lowerBound + 1, model.getPickedValue());
        model = new Model() {
            @Override
            protected double getRandomValueFrom0To1() {
                return 1.0 - 6E-17;
            }
        };
        model.setGameRange(lowerBound, upperBound);
        model.pickNumber();
        assertEquals(upperBound - 1, model.getPickedValue());
        model = new Model() {
            @Override
            protected double getRandomValueFrom0To1() {
                return 0.5;
            }
        };
        model.setGameRange(0, 100);
        model.pickNumber();
        assertEquals(50, model.getPickedValue());
        model.setGameRange(0, 101);
        model.pickNumber();
        assertEquals(51, model.getPickedValue());
    }
}
