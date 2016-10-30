package training.game.greater_less;

import training.game.greater_less.model.ModelStateSnapshotDTO;
import training.game.greater_less.model.RoundResult;
import org.junit.Assert;
import org.junit.Test;

import static training.game.greater_less.model.ModelStateDTOTest.getValidModelState;

/**
 * This test class is using for test view unit
 * @author oleksij.onysymchuk@gmail
 */
public class ViewTest {

    /**
     * check view unit for unimplemented view round results
     */
    @Test
    public void testGetStringFromInputCheckResult() {
        View view = new View();
        ModelStateSnapshotDTO modelState = getValidModelState();
        Exception exception = null;
        RoundResult roundResultGlobal = null;
        try {
            for (RoundResult roundResult : RoundResult.values()) {
                roundResultGlobal = roundResult;
                modelState.setRoundResult(roundResult);
                view.showRoundInfo(modelState);
            }
        } catch (Exception e) {
            Assert.assertTrue("Exception must not appear! Round result " + roundResultGlobal + "not implemented", false);
        }
    }

}
