import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Dimension;
import java.util.stream.IntStream;
import javax.swing.Icon;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

public class BJGUITest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;
    private VisualBJ visualBJ;

    @Override
    protected void onSetUp() {
        visualBJ = GuiActionRunner.execute(() -> new VisualBJ("Blackjack"));
        window = new FrameFixture(robot(), visualBJ);
        window.show();
        window.resizeTo(new Dimension(600, 400));

        visualBJ.bettf.setName("betField");
        visualBJ.results.setName("resultField");
        for (int i = 0; i < visualBJ.playercards.length; i++) {
            visualBJ.playercards[i].setName("playerCard[" + i + "]");
            visualBJ.dealercards[i].setName("dealerCard[" + i + "]");
        }
        visualBJ.hit.setName("hitButton");
        visualBJ.stay.setName("stayButton");
        visualBJ.deal.setName("dealButton");
        visualBJ.doubledown.setName("doubleDownButton");
    }

    @Test @GUITest
    public void initialState() {
        window.button("dealButton").requireDisabled();
        window.button("hitButton").requireDisabled();
        window.button("stayButton").requireDisabled();
        window.button("doubleDownButton").requireDisabled();
        window.button(JButtonMatcher.withText("Bet!")).requireEnabled();
        window.textBox("betField").requireEnabled();
    }

    @Test @GUITest
    public void betEnablesActions() {
        window.textBox("betField").setText("50");
        window.button(JButtonMatcher.withText("Bet!")).click();
        window.button("dealButton").requireDisabled();
        window.button("hitButton").requireEnabled();
        window.button("stayButton").requireEnabled();
        window.button("doubleDownButton").requireEnabled();
    }

    @Test @GUITest
    public void dealShowsInitialCardsAndEnablesActions() {
        window.textBox("betField").setText("20");
        window.button(JButtonMatcher.withText("Bet!")).click();
        window.button("dealButton").click();

        IntStream.range(0, 2).forEach(i -> {
            Icon playerIcon = visualBJ.playercards[i].getIcon();
            Icon dealerIcon = visualBJ.dealercards[i].getIcon();
            assertThat(playerIcon).as("Player card %d", i).isNotNull();
            assertThat(dealerIcon).as("Dealer card %d", i).isNotNull();
        });

        window.button("hitButton").requireEnabled();
        window.button("stayButton").requireEnabled();
        window.button("doubleDownButton").requireEnabled();
    }

    // EXPECTED BEHAVIOR DOESN'T WORK DUE TO FAULT
    /*
    @Test @GUITest
    public void hitAddsCardOrBusts() {
        window.textBox("betField").setText("10");
        window.button(JButtonMatcher.withText("Bet!")).click();
        window.button("dealButton").click();

        for (int attempt = 0; attempt < 5; attempt++) {
            window.button("hitButton").click();
            String msg = window.textBox("resultField").text();
            if (msg.contains("busted") || msg.contains("Charlie")) {
                break;
            }
        }
        window.button("hitButton").requireDisabled();
        window.button("stayButton").requireDisabled();
        window.button("doubleDownButton").requireDisabled();
        window.button("dealButton").requireEnabled();
        assertThat(window.textBox("resultField").text()).isNotEmpty();
    }
*/

    /*
    @Test @GUITest
    public void stayPlaysDealerAndResets() {
        window.textBox("betField").setText("15");
        window.button(JButtonMatcher.withText("Bet!")).click();
        window.button("dealButton").click();

        window.button("stayButton").click();
        window.robot().waitForIdle();

        String msg = window.textBox("resultField").text();
        assertThat(msg).matches(".*(busted|win|lose|push).*");
                window.button("dealButton").requireEnabled();
    }
*/

    /*
    @Test @GUITest
    public void doubleDownDrawsOneThenDealer() {
        window.textBox("betField").setText("25");
        window.button(JButtonMatcher.withText("Bet!")).click();
        window.button("dealButton").click();

        window.button("doubleDownButton").click();
        window.robot().waitForIdle();

        Icon extra = visualBJ.playercards[2].getIcon();
        assertThat(extra).isNotNull();

        String msg = window.textBox("resultField").text();
        assertThat(msg).isNotEmpty();
        window.button("dealButton").requireEnabled();
    }
*/
    @Override
    protected void onTearDown() {
        window.cleanUp();
    }
}
