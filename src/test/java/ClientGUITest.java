import static org.junit.Assert.*;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.runner.GUITestRunner;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.edt.GuiActionRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GUITestRunner.class)
public class ClientGUITest extends AssertJSwingJUnitTestCase {
    private FrameFixture clientWindow;
    private Client client;


    @Override
    protected void onSetUp() {
        client = GuiActionRunner.execute(() -> new Client("Casino"));
        clientWindow = new FrameFixture(robot(), client);
        clientWindow.show();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onTearDown() {
        clientWindow.cleanUp();
    }

    @Test @GUITest
    public void initialState_showsLabelAndAllButtons() {
        clientWindow.label().requireText("Which table would you like to play?");
        clientWindow.button(JButtonMatcher.withText("BlackJack")).requireEnabled();
        clientWindow.button(JButtonMatcher.withText("Poker")).requireEnabled();
        clientWindow.button(JButtonMatcher.withText("HighLow")).requireEnabled();
        clientWindow.button(JButtonMatcher.withText("Texas Holdem")).requireEnabled();
    }

    @Test @GUITest
    public void clickingBJLaunchesBJ() {
        clientWindow.button(JButtonMatcher.withText("BlackJack")).click();

        assertFalse(client.isVisible());

        boolean found = false;
        for (java.awt.Frame frame : java.awt.Frame.getFrames()) {
            if ("Blackjack".equals(frame.getTitle()) && frame.isVisible()) {
                found = true;
                frame.dispose();
                break;
            }
        }

        assertTrue(found);
    }

    @Test @GUITest
    public void clickingPokerLaunchesPoker() {
        clientWindow.button(JButtonMatcher.withText("Poker")).click();

        assertFalse(client.isVisible());

        boolean found = false;
        for (java.awt.Frame frame : java.awt.Frame.getFrames()) {
            if ("Poker".equals(frame.getTitle()) && frame.isVisible()) {
                found = true;
                frame.dispose();
                break;
            }
        }

        assertTrue(found);
    }

    @Test @GUITest
    public void clickingHLLaunchesHL() {
        clientWindow.button(JButtonMatcher.withText("HighLow")).click();

        assertFalse(client.isVisible());

        boolean found = false;
        for (java.awt.Frame frame : java.awt.Frame.getFrames()) {
            if ("HighLow".equals(frame.getTitle()) && frame.isVisible()) {
                found = true;
                frame.dispose();
                break;
            }
        }

        assertTrue(found);
    }

    @Test @GUITest
    public void clickingTHLaunchesTH() {
        clientWindow.button(JButtonMatcher.withText("Texas Holdem")).click();

        assertFalse(client.isVisible());

        boolean found = false;
        for (java.awt.Frame frame : java.awt.Frame.getFrames()) {
            if ("Texas Holdem".equals(frame.getTitle()) && frame.isVisible()) {
                found = true;
                frame.dispose();
                break;
            }
        }

        assertTrue(found);
    }
}