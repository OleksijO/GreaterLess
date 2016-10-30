package training.game.greater_less.stubs;

import training.game.greater_less.View;
import training.game.greater_less.model.ModelStateSnapshotDTO;

/**
 * Created by oleksij.onysymchuk@gmail on 30.10.2016.
 */
public class StubView extends View {
    private int greetingCounter=0;
    private int roundInfoCounter=0;
    private int showPromtCounter = 0;
    private int showStatCounter=0;
    private int errorsCounter=0;

    @Override
    public void showGreeting(int lowerBound, int upperBound) {
        greetingCounter++;
    }

    @Override
    public void showRoundInfo(ModelStateSnapshotDTO roundInfo) {
        roundInfoCounter++;
    }

    @Override
    public void showPromt() {
        showPromtCounter++;
    }

    @Override
    public void showStatistics(ModelStateSnapshotDTO modelState) {
        showStatCounter++;
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        errorsCounter++;
    }

    public int getGreetingCounter() {
        return greetingCounter;
    }

    public int getRoundInfoCounter() {
        return roundInfoCounter;
    }

    public int getShowPromtCounter() {
        return showPromtCounter;
    }

    public int getShowStatCounter() {
        return showStatCounter;
    }

    public int getErrorsCounter() {
        return errorsCounter;
    }
}
