package game.greater_less;

import game.greater_less.model.ModelStateDTO;
import game.greater_less.model.RoundResult;
import org.junit.Assert;
import org.junit.Test;

import static game.greater_less.model.ModelStateDTOTest.getValidModelState;

/**
 * Created by oleksij.onysymchuk@gmail on 30.10.2016.
 */
public class ViewTest {

    @Test
    public void testGetStringFromInputCheckResult() {

        //check for unimplemented view round results
        View view = new View();
        ModelStateDTO modelState = getValidModelState();
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
