package game.greater_less.model;

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
    public void testInit() {
        Model model = new Model();
        ModelStateSnapshotDTO modelState = model.getModelState();
        assertTrue("Model state snapshot must be invalid afer init (due to roundResult=null)", !modelState.isValid());
        assertEquals("Lower bound of game range must be 0 after init", 0, modelState.getCurrentLowerBound());
        assertEquals("Upper bound of game range must be 100 after init", 100, modelState.getCurrentUpperBound());
        assertEquals("History size must be 0 after init", 0, modelState.getUserInputHistory().size());
        assertNull("Round result must be null after init", modelState.getRoundResult());
        assertEquals("Correct tries counter must be 0 after init", 0, modelState.getUserCorrectInputCount());
        assertEquals("Incorrect tries counter must be 0 after init", 0, modelState.getUserIllegalInputCount());
    }

    @Test
    public void testOutOfBoundResults(){
        Model model = new Model();
        model.performRoundWithUserInput("-1");
        ModelStateSnapshotDTO modelState = model.getModelState();
        assertEquals(RoundResult.OUT_OF_BOUNDS, modelState.getRoundResult());
        model.performRoundWithUserInput("101");
        modelState = model.getModelState();
        assertEquals(RoundResult.OUT_OF_BOUNDS, modelState.getRoundResult());
        model.performRoundWithUserInput("50");
        modelState = model.getModelState();
        assertNotEquals(RoundResult.OUT_OF_BOUNDS, modelState.getRoundResult());
    }

    @Test
    public void testIllegalInputResults(){
        Model model = new Model();
        model.performRoundWithUserInput("lsdcnjsnd");
        ModelStateSnapshotDTO modelState = model.getModelState();
        assertEquals(RoundResult.ILLEGAL_INPUT, modelState.getRoundResult());
        model.performRoundWithUserInput(" ");
        modelState = model.getModelState();
        assertEquals(RoundResult.ILLEGAL_INPUT, modelState.getRoundResult());
    }

    @Test
    public void testRangeChanges(){
        Model model = new Model();
        model.performRoundWithUserInput("-1");
        ModelStateSnapshotDTO modelState = model.getModelState();
        assertEquals(0, modelState.getCurrentLowerBound());
        model.performRoundWithUserInput("101");
        modelState = model.getModelState();
        assertEquals(100, modelState.getCurrentUpperBound());
        model.performRoundWithUserInput("0");
        modelState = model.getModelState();
        assertEquals(1, modelState.getCurrentLowerBound());
        model.performRoundWithUserInput("100");
        modelState = model.getModelState();
        assertEquals(99, modelState.getCurrentUpperBound());
    }

    @Test
    public void testHistory(){
        Model model = new Model();
        model.setIncludeIllegalNonIntegerInputsToHistory(true);
        model.performRoundWithUserInput("-1");
        ModelStateSnapshotDTO modelState = model.getModelState();
        assertEquals(1,modelState.getUserInputHistory().size());
        assertEquals(1,modelState.getUserIllegalInputCount());
        assertEquals(0, modelState.getUserCorrectInputCount());
        model.performRoundWithUserInput("50");
        modelState = model.getModelState();
        assertEquals(2,modelState.getUserInputHistory().size());
        assertEquals(1,modelState.getUserIllegalInputCount());
        assertEquals(1, modelState.getUserCorrectInputCount());
        model.performRoundWithUserInput("sss");
        modelState = model.getModelState();
        assertEquals(3,modelState.getUserInputHistory().size());
        assertEquals(2,modelState.getUserIllegalInputCount());
        assertEquals(1, modelState.getUserCorrectInputCount());
        model.setIncludeIllegalNonIntegerInputsToHistory(false);
        model.performRoundWithUserInput("sss");
        modelState = model.getModelState();
        assertEquals(3,modelState.getUserInputHistory().size());
        assertEquals(2,modelState.getUserIllegalInputCount());
        assertEquals(1, modelState.getUserCorrectInputCount());
    }

    @Test
    public void testGameProcess() throws NoSuchFieldException, IllegalAccessException {
        Model model = new Model();
        model.setIncludeIllegalNonIntegerInputsToHistory(false);
        Field field = Model.class.getDeclaredField("pickedNumber");
        field.setAccessible(true);
        field.set(model, 50);
        model.performRoundWithUserInput("40");
        ModelStateSnapshotDTO modelState = model.getModelState();
        assertEquals(41, modelState.getCurrentLowerBound());
        assertEquals(100, modelState.getCurrentUpperBound());
        assertEquals(RoundResult.LESS_THAN_PICKED_NUMBER, modelState.getRoundResult());
        model.performRoundWithUserInput("60");
        modelState = model.getModelState();
        assertEquals(41, modelState.getCurrentLowerBound());
        assertEquals(59, modelState.getCurrentUpperBound());
        assertEquals(RoundResult.GREATER_THAN_PICKED_NUMBER, modelState.getRoundResult());
        model.performRoundWithUserInput("40");
        modelState = model.getModelState();
        assertEquals(41, modelState.getCurrentLowerBound());
        assertEquals(59, modelState.getCurrentUpperBound());
        assertEquals(RoundResult.OUT_OF_BOUNDS, modelState.getRoundResult());
        model.performRoundWithUserInput("60");
        modelState = model.getModelState();
        assertEquals(41, modelState.getCurrentLowerBound());
        assertEquals(59, modelState.getCurrentUpperBound());
        assertEquals(RoundResult.OUT_OF_BOUNDS, modelState.getRoundResult());
        model.performRoundWithUserInput("q5");
        modelState = model.getModelState();
        assertEquals(41, modelState.getCurrentLowerBound());
        assertEquals(59, modelState.getCurrentUpperBound());
        assertEquals(RoundResult.ILLEGAL_INPUT, modelState.getRoundResult());
        model.performRoundWithUserInput("50");
        modelState = model.getModelState();
        assertEquals(41, modelState.getCurrentLowerBound());
        assertEquals(59, modelState.getCurrentUpperBound());
        assertEquals(RoundResult.EQUALS_TO_PICKED_NUMBER, modelState.getRoundResult());
    }


}
