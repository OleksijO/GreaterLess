package game.greater_less.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This test class is using for test validation of model state snapshot
 * @author oleksij.onysymchuk@gmail
 */
public class ModelStateDTOTest {

    /**
     * @return returns valid model state snapshot DTO for tests
     */
    public static ModelStateDTO getValidModelState() {
        ModelStateDTO modelState = new ModelStateDTO();
        modelState.setUserCorrectInputCount(1);
        modelState.setUserIllegalInputCount(1);
        List<String> usertries = new ArrayList<>();
        usertries.add("t5");
        usertries.add("5");
        modelState.setUserTries(usertries);
        modelState.setCurrentUpperBound(5);
        modelState.setCurrentLowerBound(5);
        modelState.setRoundResult(RoundResult.EQUALS_TO_PICKED_NUMBER);
        return modelState;
    }

    /**
     * tests static method? which generating valid model state snapshot DTO
     */
    @Test
    public void testValidModelState() {
        ModelStateDTO modelState = getValidModelState();
        assertTrue(modelState.isValid());
    }

    @Test
    public void testNonValidModelStateIllegalBounds() {
        ModelStateDTO modelState = getValidModelState();
        modelState.setCurrentLowerBound(modelState.getCurrentUpperBound() * 5);
        assertFalse(modelState.isValid());
    }
    @Test
    public void testNonValidModelStateLowerBound() {
        ModelStateDTO modelState = getValidModelState();
        modelState.setCurrentLowerBound(Model.LOWER_BOUND-1);
        assertFalse(modelState.isValid());
    }
    @Test
    public void testNonValidModelStateUpperBound() {
        ModelStateDTO modelState = getValidModelState();
        modelState.setCurrentUpperBound(Model.UPPER_BOUND+1);
        assertFalse(modelState.isValid());
    }
    @Test
    public void testNonValidModelStateUserTries() {
        ModelStateDTO modelState = getValidModelState();
        modelState.setUserTries(null);
        assertFalse(modelState.isValid());
    }
    @Test
    public void testNonValidModelStateRoundResult() {
        ModelStateDTO modelState = getValidModelState();
        modelState.setRoundResult(null);
        assertFalse(modelState.isValid());
    }
    @Test
    public void testNonValidModelStateUserCorrectInputCount() {
        ModelStateDTO modelState = getValidModelState();
        modelState.setUserCorrectInputCount(modelState.getUserTries().size()+1);
        assertFalse(modelState.isValid());
    }
}
