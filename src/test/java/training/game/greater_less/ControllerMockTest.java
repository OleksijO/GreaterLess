package training.game.greater_less;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * This test class is using for test controller unit
 *
 * @author oleksij.onysymchuk@gmail
 */
public class ControllerMockTest {
    @Mock
    private Model model;
    @Mock
    private View view;
    @Spy
    private Controller controller;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        controller.setModel(model);
        controller.setView(view);
    }

    @Test
    public void testUserInputs() throws NoSuchFieldException, IllegalAccessException {

        String input[] = {"-50", "140", "ss", "40", "40", "60", "150", "50"};
        Integer[] inputValues = {40, 60, 50};
        Answer<String> answerForInputWithScanner = new Answer<String>() {
            private int count = 0;

            public String answer(InvocationOnMock invocation) {
                return input[count++];
            }
        };

        doAnswer(answerForInputWithScanner).when(controller).inputStringValueWithScanner(any());
        when(model.getLowerBound()).thenReturn(-50, 40);
        when(model.getUpperBound()).thenReturn(150, 150, 60);
        when(model.getPickedValue()).thenReturn(50);
        when(model.checkUserValue(anyInt())).thenReturn(-1, 1, 0);
        when(model.getUserInputHistory()).thenReturn(new ArrayList<Integer>());
        when(model.isUserInputValueIsInRange(anyInt())).thenReturn(true, false, true, false, true);


        controller.playGame();
        verify(view, times(1)).showGreeting();
        verify(view, times(8)).showMessage(anyString());
        verify(view, times(1)).showYouWinAndStatisitics(any());


    }

}
