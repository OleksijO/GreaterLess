package training.game.greater_less.stubs;

import training.game.greater_less.View;
import training.game.greater_less.model.ModelStateSnapshotDTO;

/**
 * Created by oleksij.onysymchuk@gmail on 30.10.2016.
 */
public class StubView extends View {
    @Override
    public void showGreeting(int lowerBound, int upperBound) {
        super.showGreeting(lowerBound, upperBound);
    }

    @Override
    public void showRoundInfo(ModelStateSnapshotDTO roundInfo) {
        super.showRoundInfo(roundInfo);
    }

    @Override
    public void showPromt() {
        super.showPromt();
    }

    @Override
    public void showStatistics(ModelStateSnapshotDTO modelState) {
        super.showStatistics(modelState);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        super.showErrorMessage(errorMessage);
    }

    public boolean isRightBehaivour(){
        return true;
    }
}
